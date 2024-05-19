package com.vozhov.caesarapi.payload.request;

import lombok.Data;

@Data
public class DistributeRequest {
    int difficulty;
    long projectId;
    long groupId;
}
