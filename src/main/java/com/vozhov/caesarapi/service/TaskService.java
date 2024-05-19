package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.ProjectEntity;
import com.vozhov.caesarapi.entity.TaskEntity;
import com.vozhov.caesarapi.entity.TaskStatus;
import com.vozhov.caesarapi.entity.UserEntity;
import com.vozhov.caesarapi.payload.request.DistributeRequest;
import com.vozhov.caesarapi.payload.request.TaskRequest;

import java.util.List;

public interface TaskService {
    void createTask(TaskRequest taskRequest);
    void createTaskToPanel(TaskRequest taskRequest);
    List<TaskEntity> getTasks();
    List<TaskEntity> getTaskByProject(Long id);
    List<TaskEntity> getTaskByProjectAndStatus(Long id, TaskStatus status);
    TaskEntity getTask(Long id);
    void editTask(TaskRequest request);

    UserEntity distribute(DistributeRequest request);

    void deleteTask(Long id);
}
