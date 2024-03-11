package com.vozhov.caesarapi.payload.request;

import lombok.Data;

@Data
public class TaskRequest {
    Long id;
    String name;
    String description;

    Long projectId;
    Long panelId;
}
