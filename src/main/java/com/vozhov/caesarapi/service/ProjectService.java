package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.ProjectEntity;
import com.vozhov.caesarapi.entity.UserEntity;
import com.vozhov.caesarapi.payload.request.ProjectRequest;

import java.util.List;

public interface ProjectService {
    void createProject(ProjectRequest projectRequest);
    List<ProjectEntity> getProjects();
    ProjectEntity getProject(Long id);
    void editProject(ProjectRequest projectRequest);

    void addEmployees(Long id, List<String> employees);
    void addEmployee(ProjectEntity project, String employee);
    List<UserEntity> getEmployees(Long id);
}