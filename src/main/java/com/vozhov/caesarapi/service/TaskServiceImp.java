package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.*;
import com.vozhov.caesarapi.payload.request.DistributeRequest;
import com.vozhov.caesarapi.payload.request.TaskRequest;
import com.vozhov.caesarapi.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final PanelRepository panelRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final DeskRepository deskRepository;
    private final DeskService deskService;

    @Override
    public void createTask(TaskRequest taskRequest) {
        Optional<UserEntity> uo = userRepository.findByUsername(taskRequest.getAuthor());
        Optional<ProjectEntity> pe = projectRepository.findById(taskRequest.getProjectId());

        if(uo.isPresent() && pe.isPresent()) {
            TaskEntity te = new TaskEntity();
            te.setName(taskRequest.getName());
            te.setAuthor(uo.get());
            te.setStatus(TaskStatus.OPENED);
            te.setType(TaskType.TASK);
            te.setPriority(TaskPriority.AVERAGE);

            te.setProjectEntity(pe.get());
            taskRepository.save(te);
        }
    }

    @Override
    public void createTaskToPanel(TaskRequest taskRequest) {
        Optional<UserEntity> uo = userRepository.findByUsername(taskRequest.getAuthor());
        Optional<ProjectEntity> pe = projectRepository.findById(taskRequest.getProjectId());

        if(uo.isPresent() && pe.isPresent()) {
            TaskEntity te = new TaskEntity();
            te.setName(taskRequest.getName());
            te.setAuthor(uo.get());
            te.setStatus(TaskStatus.OPENED);
            te.setType(TaskType.TASK);
            te.setPriority(TaskPriority.AVERAGE);

            te.setProjectEntity(pe.get());
            taskRepository.save(te);
            taskRepository.flush();

            deskService.addTaskToPanel(te.getId(), taskRequest.getPanelId());
        }
    }

    @Override
    public List<TaskEntity> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<TaskEntity> getTaskByProject(Long id) {
        Optional<ProjectEntity> ope = projectRepository.findById(id);
        return ope.map(taskRepository::findAllByProjectEntity).orElse(null);
    }

    @Override
    public List<TaskEntity> getTaskByProjectAndStatus(Long id, TaskStatus status) {
        Optional<ProjectEntity> ope = projectRepository.findById(id);
        return ope.map(projectEntity -> taskRepository.findAllByProjectEntityAndStatus(projectEntity, status)).orElse(null);
    }

    @Override
    public TaskEntity getTask(Long id) {
        Optional<TaskEntity> te = taskRepository.findById(id);
        return te.orElse(null);
    }

    @Override
    public void editTask(TaskRequest request) {
        Optional<TaskEntity> te = taskRepository.findById(request.getId());
        if (te.isPresent()) {
            TaskEntity t = te.get();
            t.setName(request.getName());
            t.setDescription(request.getDescription());
            if (t.getStatus() != TaskStatus.COMPLETED && request.getStatus() == TaskStatus.COMPLETED)
                t.setFinishedDate(new Date());
            if (t.getStatus() != request.getStatus()) {
                List<PanelEntity> panels = panelRepository.findAllByTasksIsContaining(t);
                for (PanelEntity panel : panels) {
                    Optional<DeskEntity> deo = deskRepository.findById(panel.getDeskId());
                    if (deo.isPresent()) {
                        DeskEntity de = deo.get();
                        deskService.removeTaskFromPanel(t.getId(), panel.getId());
                        PanelEntity newPanel = de.getPanels().get(request.getStatus());
                        if (newPanel != null) {
                            deskService.addTaskToPanel(t.getId(), newPanel.getId());
                        }
                    }
                }
            }
            t.setStatus(request.getStatus());
            t.setType(request.getType());
            t.setPriority(request.getPriority());
            t.setDifficulty(request.getDifficulty());

            if(request.getAssignee() != null) {
                Optional<UserEntity> uo = userRepository.findByUsername(request.getAssignee());
                uo.ifPresent(t::setAssignee);
            } else if(t.getAssignee() != null) t.setAssignee(null);

            if (request.getGroup() != null) {
                Optional<GroupEntity> ge = groupRepository.findById(request.getGroup());
                ge.ifPresent(t::setGroup);
            } else if (t.getGroup() != null) t.setGroup(null);

            t.setStartDate(request.getStartDate());
            t.setDeadline(request.getDeadline());

            taskRepository.save(t);
        }
    }

    @Override
    public UserEntity distribute(DistributeRequest request) {
        Optional<ProjectEntity> po = projectRepository.findById(request.getProjectId());
        if (po.isPresent()) {
            ProjectEntity pe = po.get();
            List<UserEntity> emps = pe.getEmployees();
            Map<UserEntity, Float> empCof = new HashMap<>();
            for (UserEntity ue : emps) {
                List<TaskEntity> completedTasks = taskRepository.findAllByAssigneeAndFinishedDateBetween(ue, new Date(System.currentTimeMillis() - 2628000000L), new Date());
                float empSkill = 0;
                int c = 0;
                for (TaskEntity task : completedTasks) {
                    c++;
                    empSkill += task.getDifficulty();
                }
                if (c > 0) empSkill /= c;
                float skill = getValueThrough(empSkill, 4, request.getDifficulty(), 1);
                float workload = getValueThrough(taskRepository.findAllByAssigneeAndStatus(ue, TaskStatus.IN_WORK).size(), 4, 0, 4);
                float cof = (skill + workload) / 2;
                empCof.put(ue, cof);
            }
            UserEntity resultedEmp = null;
            float maxCof = 0;
            for (Map.Entry<UserEntity, Float> entry : empCof.entrySet()) {
                if (entry.getValue() > maxCof) {
                    maxCof = entry.getValue();
                    resultedEmp = entry.getKey();
                }
            }
            return resultedEmp;
        }
        return null;
    }

    @Override
    public void deleteTask(Long id) {
        Optional<TaskEntity> te = taskRepository.findById(id);
        if (te.isPresent()) {
            TaskEntity t = te.get();
            List<PanelEntity> panels = panelRepository.findAllByTasksIsContaining(t);
            for (PanelEntity panel : panels)
                deskService.removeTaskFromPanel(t.getId(), panel.getId());
            taskRepository.delete(t);
        }
    }

    public float getValueThrough(float value, int sq, float center, float width) {
        return (float) (1 / (1 + Math.pow((double) (value - center) / width, sq)));
    }
}