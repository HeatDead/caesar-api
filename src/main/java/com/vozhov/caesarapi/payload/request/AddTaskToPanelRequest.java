package com.vozhov.caesarapi.payload.request;

import lombok.Data;

@Data
public class AddTaskToPanelRequest {
    Long panelId;
    Long taskId;
}
