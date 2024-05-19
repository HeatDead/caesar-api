package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.*;
import com.vozhov.caesarapi.payload.request.desk.DeskRequest;
import com.vozhov.caesarapi.payload.request.desk.PanelRequest;
import com.vozhov.caesarapi.repository.DeskRepository;
import com.vozhov.caesarapi.repository.PanelRepository;
import com.vozhov.caesarapi.repository.ProjectRepository;
import com.vozhov.caesarapi.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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

            DeskEntity d = deskRepository.saveAndFlush(de);
            d.addPanel(createPanel(d.getId(), TaskStatus.OPENED, "Открыты"));
            d.addPanel(createPanel(d.getId(), TaskStatus.IN_WORK, "В работе"));
            d.addPanel(createPanel(d.getId(), TaskStatus.COMPLETED, "Завершены"));

            deskRepository.save(d);
        }
    }

    public PanelEntity createPanel(Long deskId, TaskStatus status, String name) {
        PanelEntity panelEntity = new PanelEntity();
        panelEntity.setDeskId(deskId);
        panelEntity.setStatus(status);
        panelEntity.setName(name);

        panelRepository.save(panelEntity);
        return panelEntity;
    }

    @Override
    public void editDesk(DeskRequest request) {
        Optional<DeskEntity> deo = deskRepository.findById(request.getId());
        if(deo.isPresent()) {
            DeskEntity de = deo.get();
            de.setName(request.getName());

            deskRepository.save(de);
        }
    }

    @Override
    public List<PanelEntity> getPanels(Long deskId) {
        Optional<DeskEntity> op = deskRepository.findById(deskId);
        //return op.map(panelRepository::findAllByDeskEntity).orElse(null);
        return op.map(deskEntity -> deskEntity.getPanels().values().stream().toList()).orElse(null);
    }

    @Override
    public void createPanel(PanelRequest request) {
        Optional<DeskEntity> deo = deskRepository.findById(request.getDeskId());
        if(deo.isPresent()) {
            DeskEntity de = deo.get();
            if (!de.getPanels().containsKey(request.getStatus())) {
                de.addPanel(createPanel(de.getId(), request.getStatus(), request.getName()));
                deskRepository.saveAndFlush(de);
            }
        }
    }

    @Override
    public void editPanel(PanelRequest request) {
        Optional<PanelEntity> op = panelRepository.findById(request.getId());
        if(op.isPresent()) {
            PanelEntity pe = op.get();
            pe.setName(request.getName());

            panelRepository.save(pe);
        }
    }

    @Override
    public void addTaskToPanel(Long taskId, Long panelId) {
        Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(taskId);
        Optional<PanelEntity> optionalPanelEntity = panelRepository.findById(panelId);

        if(optionalTaskEntity.isPresent() && optionalPanelEntity.isPresent()) {
            PanelEntity pe = optionalPanelEntity.get();
            TaskEntity te = optionalTaskEntity.get();
            if (te.getStatus() != TaskStatus.COMPLETED && pe.getStatus() == TaskStatus.COMPLETED)
                te.setFinishedDate(new Date());
            te.setStatus(pe.getStatus());
            taskRepository.save(te);

            pe.addTask(te);
            panelRepository.save(pe);
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

    @Override
    public List<TaskStatus> getAvailableStatuses(Long deskId) {
        List<TaskStatus> statuses = new ArrayList<>();
        Optional<DeskEntity> deo = deskRepository.findById(deskId);
        if (deo.isPresent()) {
            Map<TaskStatus, PanelEntity> panels = deo.get().getPanels();
            for (TaskStatus status : TaskStatus.values()) {
                if (!panels.containsKey(status))
                    statuses.add(status);
            }
        }
        return statuses;
    }
}
