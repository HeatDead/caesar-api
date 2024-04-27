package com.vozhov.caesarapi.payload.request.desk;

import lombok.Data;

@Data
public class PanelRequest {
    Long id;
    String name;
    Long deskId;
}
