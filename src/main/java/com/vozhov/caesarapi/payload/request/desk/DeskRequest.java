package com.vozhov.caesarapi.payload.request.desk;

import lombok.Data;

@Data
public class DeskRequest {
    Long id;
    String name;
    Long projectId;
}
