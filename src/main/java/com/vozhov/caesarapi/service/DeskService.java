package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.DeskEntity;
import com.vozhov.caesarapi.entity.PanelEntity;
import com.vozhov.caesarapi.payload.request.desk.DeskRequest;
import com.vozhov.caesarapi.payload.request.desk.PanelRequest;

import java.util.List;

public interface DeskService {
    DeskEntity getDesk(Long id);
    List<DeskEntity> getDesks();
    List<DeskEntity> getDesksByProject(Long id);
    void createDesk(DeskRequest request);
    void editDesk(DeskRequest request);
    List<PanelEntity> getPanels(Long deskId);
    void createPanel(PanelRequest request);
    void editPanel(PanelRequest request);
    void addTaskToPanel(Long taskId, Long panelId);
    void removeTaskFromPanel(Long taskId, Long panelId);
}