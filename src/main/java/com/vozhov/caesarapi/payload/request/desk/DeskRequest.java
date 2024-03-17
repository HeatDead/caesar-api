package com.vozhov.caesarapi.payload.request.desk;

import lombok.Data;

@Data
public class DeskRequest {
    String name;
    Long projectId;
}
