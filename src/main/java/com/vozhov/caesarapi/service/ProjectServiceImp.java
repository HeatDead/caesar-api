package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.ProjectEntity;
import com.vozhov.caesarapi.entity.UserEntity;
import com.vozhov.caesarapi.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public void createProject(String name, String description) {
        ProjectEntity pe = new ProjectEntity();
        pe.setName(name);
        pe.setDescription(description);

        projectRepository.save(pe);
    }

    @Override
    public List<ProjectEntity> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void addEmployees(ProjectEntity project, List<UserEntity> employees) {
        for (UserEntity u : employees) {
            addEmployee(project, u);
        }
    }

    @Override
    public void addEmployee(ProjectEntity project, UserEntity employee) {
        project.addEmployee(employee);
        projectRepository.save(project);
    }
}