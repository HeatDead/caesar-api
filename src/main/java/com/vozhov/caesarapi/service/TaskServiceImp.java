package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.ProjectEntity;
import com.vozhov.caesarapi.entity.TaskEntity;
import com.vozhov.caesarapi.entity.UserEntity;
import com.vozhov.caesarapi.payload.request.TaskRequest;
import com.vozhov.caesarapi.repository.ProjectRepository;
import com.vozhov.caesarapi.repository.TaskRepository;
import com.vozhov.caesarapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final DeskService deskService;

    @Override
    public void createTask(TaskRequest taskRequest) {
        Optional<UserEntity> uo = userRepository.findByUsername(taskRequest.getAuthor());
        Optional<ProjectEntity> pe = projectRepository.findById(taskRequest.getProjectId());

        if(uo.isPresent() && pe.isPresent()) {
            TaskEntity te = new TaskEntity();
            te.setName(taskRequest.getName());
            te.setAuthor(uo.get());

            te.setProjectEntity(pe.get());
            taskRepository.save(te);
        }
    }

    @Override
    public void createTaskToPanel(String name, Long projectId, Long panelId) {
        TaskEntity te = new TaskEntity();
        te.setName(name);

        Optional<ProjectEntity> pe = projectRepository.findById(projectId);
        if(pe.isPresent())
            te.setProjectEntity(pe.get());
        else return;
        taskRepository.save(te);
        taskRepository.flush();

        System.out.println(te.getId() + " " + panelId);

        deskService.addTaskToPanel(te.getId(), panelId);
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

            if(request.getAssignee() != null) {
                Optional<UserEntity> uo = userRepository.findByUsername(request.getAssignee());
                uo.ifPresent(t::setAssignee);
            }

            t.setStartDate(request.getStartDate());
            t.setDeadline(request.getDeadline());

            taskRepository.save(t);
        }
    }
}