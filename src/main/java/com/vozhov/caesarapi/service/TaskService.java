package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.ProjectEntity;
import com.vozhov.caesarapi.entity.TaskEntity;

import java.util.List;

public interface TaskService {
    void createTask(String name, String description, Long projectId);
    List<TaskEntity> getTasks();
}
