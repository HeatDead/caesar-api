package com.vozhov.caesarapi.controller;

import com.vozhov.caesarapi.entity.TaskEntity;
import com.vozhov.caesarapi.model.TaskRequest;
import com.vozhov.caesarapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public void createTask(@RequestBody TaskRequest taskRequest) {
        taskService.createTask(taskRequest.getName(), taskRequest.getDescription(), taskRequest.getProjectId());
    }

    @GetMapping
    public List<TaskEntity> getTasks() {
        return taskService.getTasks();
    }
}