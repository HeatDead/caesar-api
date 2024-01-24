package com.vozhov.caesarapi.model;

import lombok.Data;

@Data
public class TaskRequest {
    Long id;
    String name;
    String description;

    Long projectId;
}
