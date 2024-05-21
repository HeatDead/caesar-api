package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.ChartData;
import com.vozhov.caesarapi.entity.Dataset;
import com.vozhov.caesarapi.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class DashboardServiceImp implements DashboardService{
    private final TaskRepository taskRepository;
    @Override
    public ChartData getTasksData() {
        ChartData chartData = new ChartData();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM");
        Date myDate = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        List<String> labels = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        cal.add(Calendar.DATE, -30);
        for (int i = 0; i < 31; i++) {
            labels.add(dateFormat.format(cal.getTime()));
            data.add(taskRepository.findAllByFinishedDate(cal.getTime()).size());
            cal.add(Calendar.DATE, +1);
        }
        Dataset dataset = new Dataset();
        dataset.setLabel("Выполненные задачи");
        dataset.setData(data);
        chartData.setLabels(labels);
        chartData.setDatasets(List.of(dataset));
        return chartData;
    }
}
