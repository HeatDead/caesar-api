package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.ProjectEntity;
import com.vozhov.caesarapi.entity.UserEntity;

import java.util.List;

public interface ProjectService {
    void createProject(String name);
    List<ProjectEntity> getProjects();
    ProjectEntity getProject(Long id);

    void addEmployees(ProjectEntity project, List<UserEntity> employees);
    void addEmployee(ProjectEntity project, UserEntity employee);
}