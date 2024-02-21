package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.ProjectEntity;
import com.vozhov.caesarapi.entity.TaskEntity;
import com.vozhov.caesarapi.repository.ProjectRepository;
import com.vozhov.caesarapi.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    @Override
    public void createTask(String name, String description, Long projectId) {
        TaskEntity te = new TaskEntity();
        te.setName(name);
        te.setDescription(description);

        Optional<ProjectEntity> pe = projectRepository.findById(projectId);
        if(pe.isPresent())
            te.setProjectEntity(pe.get());
        else return;

        taskRepository.save(te);
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
}
