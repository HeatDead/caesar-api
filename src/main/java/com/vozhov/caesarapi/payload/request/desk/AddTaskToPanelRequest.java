package com.vozhov.caesarapi.payload.request.desk;

import lombok.Data;

@Data
public class AddTaskToPanelRequest {
    Long panelId;
    Long taskId;
}
