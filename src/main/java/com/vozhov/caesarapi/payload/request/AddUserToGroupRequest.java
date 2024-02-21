package com.vozhov.caesarapi.payload.request;

import lombok.Data;

@Data
public class AddUserToGroupRequest {
    Long userId;
    Long groupId;
}
