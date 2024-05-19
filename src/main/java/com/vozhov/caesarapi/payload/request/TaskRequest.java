package com.vozhov.caesarapi.payload.request;

import com.vozhov.caesarapi.entity.TaskPriority;
import com.vozhov.caesarapi.entity.TaskStatus;
import com.vozhov.caesarapi.entity.TaskType;
import lombok.Data;

import java.util.Date;

@Data
public class TaskRequest {
    private Long id;
    private String name;
    private String description;

    private Long projectId;
    private Long panelId;

    private TaskStatus status;
    private TaskType type;
    private TaskPriority priority;
    private Integer difficulty;

    private String author;
    private String assignee;

    private Long group;

    private Date startDate;
    private Date deadline;
}
