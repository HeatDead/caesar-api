package com.vozhov.caesarapi.controller;

import com.vozhov.caesarapi.entity.ProjectEntity;
import com.vozhov.caesarapi.payload.request.ProjectRequest;
import com.vozhov.caesarapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public void createProject(@RequestBody ProjectRequest projectRequest) {
        projectService.createProject(projectRequest.getName());
    }

    @GetMapping("/list")
    public List<ProjectEntity> getProjects() {
        return projectService.getProjects();
    }

    @GetMapping
    public ProjectEntity getProject(@RequestParam Long id) {
        return projectService.getProject(id);
    }
}