package com.vozhov.caesarapi.payload.request.desk;

import com.vozhov.caesarapi.entity.TaskStatus;
import lombok.Data;

@Data
public class PanelRequest {
    Long id;
    String name;
    Long deskId;
    TaskStatus status;
}
