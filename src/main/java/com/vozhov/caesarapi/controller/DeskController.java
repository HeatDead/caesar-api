package com.vozhov.caesarapi.controller;

import com.vozhov.caesarapi.entity.DeskEntity;
import com.vozhov.caesarapi.entity.PanelEntity;
import com.vozhov.caesarapi.entity.TaskStatus;
import com.vozhov.caesarapi.payload.request.desk.AddTaskToPanelRequest;
import com.vozhov.caesarapi.payload.request.desk.DeskRequest;
import com.vozhov.caesarapi.payload.request.desk.PanelRequest;
import com.vozhov.caesarapi.service.DeskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/statuses")
    public List<TaskStatus> getDeskAvailableStatuses(@RequestParam Long id) {
        return deskService.getAvailableStatuses(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('desk:create')")
    public void createDesk(@RequestBody DeskRequest request) {
        deskService.createDesk(request);
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('desk:update')")
    public void editDesk(@RequestBody DeskRequest request) {
        deskService.editDesk(request);
    }

    @PostMapping("/panel")
    @PreAuthorize("hasAuthority('desk:update')")
    public void createPanel(@RequestBody PanelRequest panelRequest) {
        deskService.createPanel(panelRequest);
    }

    @PostMapping("/panel/task")
    @PreAuthorize("hasAuthority('task:update')")
    public void addTaskToPanel(@RequestBody AddTaskToPanelRequest request) {
        deskService.addTaskToPanel(request.getTaskId(), request.getPanelId());
    }

    @DeleteMapping("/panel/task")
    @PreAuthorize("hasAuthority('task:update')")
    public void removeTaskFromPanel(@RequestBody AddTaskToPanelRequest request) {
        deskService.removeTaskFromPanel(request.getTaskId(), request.getPanelId());
    }

    @DeleteMapping("/panel/delete")
    @PreAuthorize("hasAuthority('desk:update')")
    public void deletePanel(@RequestParam Long id) {
        deskService.deletePanel(id);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('desk:delete')")
    public void deleteDesk(@RequestParam Long id) {
        deskService.deleteDesk(id);
    }

    @PostMapping("/panel/edit")
    @PreAuthorize("hasAuthority('desk:update')")
    public void editPanel(@RequestBody PanelRequest request) {
        deskService.editPanel(request);
    }

    @GetMapping("/panels")
    public List<PanelEntity> getPanels(Long id) {
        return deskService.getPanels(id);
    }
}