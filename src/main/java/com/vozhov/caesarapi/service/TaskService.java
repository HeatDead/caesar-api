package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.ProjectEntity;
import com.vozhov.caesarapi.entity.TaskEntity;

import java.util.List;

public interface TaskService {
    void createTask(String name, Long projectId);
    void createTaskToPanel(String name, Long projectId, Long panelId);
    List<TaskEntity> getTasks();
    List<TaskEntity> getTaskByProject(Long id);
    TaskEntity getTask(Long id);
}
