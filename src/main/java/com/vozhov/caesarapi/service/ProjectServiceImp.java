package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.ProjectEntity;
import com.vozhov.caesarapi.entity.ProjectStatus;
import com.vozhov.caesarapi.entity.UserEntity;
import com.vozhov.caesarapi.payload.request.ProjectRequest;
import com.vozhov.caesarapi.repository.ProjectRepository;
import com.vozhov.caesarapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public void createProject(ProjectRequest request) {
        Optional<UserEntity> author = userRepository.findByUsername(request.getAuthor());
        if(author.isPresent()) {
            ProjectEntity pe = new ProjectEntity();
            pe.setName(request.getName());
            pe.setAuthor(author.get());
            pe.setStatus(ProjectStatus.NEW);

            projectRepository.save(pe);
        }
    }

    @Override
    public List<ProjectEntity> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public ProjectEntity getProject(Long id) {
        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findById(id);
        return optionalProjectEntity.orElse(null);
    }

    @Override
    public void editProject(ProjectRequest projectRequest) {
        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findById(projectRequest.getId());
        if (optionalProjectEntity.isPresent()) {
            ProjectEntity pe = optionalProjectEntity.get();
            if (projectRequest.getResponsible() != null) {
                Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(projectRequest.getResponsible());
                if(optionalUserEntity.isPresent()) {
                    pe.setResponsible(optionalUserEntity.get());
                } else return;
            } else pe.setResponsible(null);
            pe.setName(projectRequest.getName());
            pe.setDescription(projectRequest.getDescription());
            pe.setStartDate(projectRequest.getStartDate());
            pe.setDeadline(projectRequest.getDeadline());

            projectRepository.save(pe);
        }
    }

    @Override
    public void addEmployees(Long id, List<String> employees) {
        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findById(id);
        if (optionalProjectEntity.isPresent()) {
            ProjectEntity project = optionalProjectEntity.get();
            project.clearEmployee();
            projectRepository.save(project);
            for (String u : employees) {
                addEmployee(project, u);
            }
        }
    }

    @Override
    public void addEmployee(ProjectEntity project, String employee) {
        Optional<UserEntity> uo = userRepository.findByUsername(employee);
        if(uo.isPresent()) {
            UserEntity ue = uo.get();
            if (!project.getEmployees().contains(ue)) {
                project.addEmployee(ue);
                projectRepository.save(project);
            }
        }
    }

    @Override
    public List<UserEntity> getEmployees(Long id) {
        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findById(id);
        if (optionalProjectEntity.isPresent()) {
            ProjectEntity pe = optionalProjectEntity.get();
            return pe.getEmployees();
        }
        return null;
    }
}