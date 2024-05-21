package com.vozhov.caesarapi.controller;

import com.vozhov.caesarapi.entity.ChartData;
import com.vozhov.caesarapi.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/charts")
@RequiredArgsConstructor
@CrossOrigin
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/tasks")
    public ChartData getTasksData() {
        return dashboardService.getTasksData();
    }
}
