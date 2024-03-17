package com.vozhov.caesarapi.payload.request.user;

import lombok.Data;

@Data
public class AddUserToGroupRequest {
    String userId;
    Long groupId;
}
