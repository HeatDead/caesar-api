package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.DeskEntity;
import com.vozhov.caesarapi.entity.PanelEntity;
import com.vozhov.caesarapi.entity.ProjectEntity;
import com.vozhov.caesarapi.entity.TaskEntity;
import com.vozhov.caesarapi.payload.request.DeskRequest;
import com.vozhov.caesarapi.payload.request.PanelRequest;
import com.vozhov.caesarapi.repository.DeskRepository;
import com.vozhov.caesarapi.repository.PanelRepository;
import com.vozhov.caesarapi.repository.ProjectRepository;
import com.vozhov.caesarapi.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeskServiceImp implements DeskService{
    private final DeskRepository deskRepository;
    private final PanelRepository panelRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Override
    public DeskEntity getDesk(Long id) {
        Optional<DeskEntity> op = deskRepository.findById(id);
        return op.orElse(null);
    }

    @Override
    public List<DeskEntity> getDesks() {
        return deskRepository.findAll();
    }

    @Override
    public List<DeskEntity> getDesksByProject(Long id) {
        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findById(id);
        return optionalProjectEntity.map(deskRepository::findAllByProjectEntity).orElse(null);
    }

    @Override
    public void createDesk(DeskRequest request) {
        Optional<ProjectEntity> pe = projectRepository.findById(request.getProjectId());
        if(pe.isPresent()) {
            DeskEntity de = new DeskEntity();
            de.setName(request.getName());
            de.setProjectEntity(pe.get());

            deskRepository.save(de);
        }
    }

    @Override
    public List<PanelEntity> getPanels(Long deskId) {
        Optional<DeskEntity> op = deskRepository.findById(deskId);
        return op.map(panelRepository::findAllByDeskEntity).orElse(null);
    }

    @Override
    public void createPanel(PanelRequest request) {
        Optional<DeskEntity> de = deskRepository.findById(request.getDeskId());
        if(de.isPresent()) {
            PanelEntity pe = new PanelEntity();
            pe.setDeskEntity(de.get());
            pe.setName(request.getName());

            panelRepository.save(pe);
        }
    }

    @Override
    public void addTaskToPanel(Long taskId, Long panelId) {
        Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(taskId);
        Optional<PanelEntity> optionalPanelEntity = panelRepository.findById(panelId);

        if(optionalTaskEntity.isPresent() && optionalPanelEntity.isPresent()) {
            optionalPanelEntity.get().addTask(optionalTaskEntity.get());
            panelRepository.save(optionalPanelEntity.get());
        }
    }
    // TODO Сделать чтобы перенос таски был одним методом с транзакцией
    @Override
    public void removeTaskFromPanel(Long taskId, Long panelId) {
        Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(taskId);
        Optional<PanelEntity> optionalPanelEntity = panelRepository.findById(panelId);

        if(optionalTaskEntity.isPresent() && optionalPanelEntity.isPresent()) {
            optionalPanelEntity.get().removeTask(optionalTaskEntity.get());
            panelRepository.save(optionalPanelEntity.get());
        }
    }
}
