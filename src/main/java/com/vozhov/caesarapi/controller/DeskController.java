package com.vozhov.caesarapi.controller;

import com.vozhov.caesarapi.entity.DeskEntity;
import com.vozhov.caesarapi.entity.PanelEntity;
import com.vozhov.caesarapi.payload.request.desk.AddTaskToPanelRequest;
import com.vozhov.caesarapi.payload.request.desk.DeskRequest;
import com.vozhov.caesarapi.payload.request.desk.PanelRequest;
import com.vozhov.caesarapi.service.DeskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desk")
@RequiredArgsConstructor
@CrossOrigin
public class DeskController {
    private final DeskService deskService;
    @GetMapping("/list")
    public List<DeskEntity> getDesks() {
        return deskService.getDesks();
    }

    @GetMapping("/project")
    public List<DeskEntity> getDesksByProject(@RequestParam Long id) {
        return deskService.getDesksByProject(id);
    }

    @GetMapping
    public DeskEntity getDesk(@RequestParam Long id) {
        return deskService.getDesk(id);
    }

    @PostMapping
    public void createDesk(@RequestBody DeskRequest request) {
        deskService.createDesk(request);
    }

    @PostMapping("/panel")
    public void createPanel(@RequestBody PanelRequest panelRequest) {
        System.out.println(panelRequest);
        deskService.createPanel(panelRequest);
    }

    @PostMapping("/panel/task")
    public void addTaskToPanel(@RequestBody AddTaskToPanelRequest request) {
        deskService.addTaskToPanel(request.getTaskId(), request.getPanelId());
    }

    @DeleteMapping("/panel/task")
    public void removeTaskFromPanel(@RequestBody AddTaskToPanelRequest request) {
        deskService.removeTaskFromPanel(request.getTaskId(), request.getPanelId());
    }

    @GetMapping("/panels")
    public List<PanelEntity> getPanels(Long id) {
        return deskService.getPanels(id);
    }
}