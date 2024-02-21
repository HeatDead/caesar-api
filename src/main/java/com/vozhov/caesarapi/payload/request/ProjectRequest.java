package com.vozhov.caesarapi.payload.request;

import lombok.Data;

@Data
public class ProjectRequest {
    Long id;
    String name;
    String description;
}
