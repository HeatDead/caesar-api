package com.vozhov.caesarapi.payload.request;

import lombok.Data;

import java.util.Date;

@Data
public class TaskRequest {
    private Long id;
    private String name;
    private String description;

    private Long projectId;
    private Long panelId;

    private String author;
    private String assignee;

    private Date startDate;
    private Date deadline;
}
