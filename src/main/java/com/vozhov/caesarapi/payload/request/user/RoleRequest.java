package com.vozhov.caesarapi.payload.request.user;

import com.vozhov.caesarapi.entity.RoleEntity;
import lombok.Data;

import java.util.Set;

@Data
public class RoleRequest {
    Long id;
    String name;
    String userId;
    Set<RoleFeatures> roleFeatures;
    RoleEntity role;
}