package com.vozhov.caesarapi.controller;

import com.vozhov.caesarapi.entity.TaskEntity;
import com.vozhov.caesarapi.payload.request.TaskRequest;
import com.vozhov.caesarapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @PreAuthorize("hasAuthority('task:create')")
    public void createTask(@RequestBody TaskRequest taskRequest) {
        taskService.createTask(taskRequest);
    }

    @PostMapping("/panel")
    @PreAuthorize("hasAuthority('task:create')")
    public void createTaskToPanel(@RequestBody TaskRequest taskRequest) {
        taskService.createTaskToPanel(taskRequest.getName(), taskRequest.getProjectId(), taskRequest.getPanelId());
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('task:update')")
    public void editTask(@RequestBody TaskRequest taskRequest) {
        taskService.editTask(taskRequest);
    }

    @GetMapping("/get")
    public TaskEntity getTask(@RequestParam Long id) {
        return taskService.getTask(id);
    }

    @GetMapping("/project")
    public List<TaskEntity> getTaskByProject(@RequestParam Long id) {
        return taskService.getTaskByProject(id);
    }

    @GetMapping
    public List<TaskEntity> getTasks() {
        return taskService.getTasks();
    }
}