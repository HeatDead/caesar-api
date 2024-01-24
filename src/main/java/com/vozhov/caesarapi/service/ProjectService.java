package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.ProjectEntity;
import com.vozhov.caesarapi.entity.UserEntity;

import java.util.List;

public interface ProjectService {
    void createProject(String name, String description);
    List<ProjectEntity> getProjects();

    void addEmployees(ProjectEntity project, List<UserEntity> employees);
    void addEmployee(ProjectEntity project, UserEntity employee);
}