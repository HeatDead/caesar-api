package com.vozhov.caesarapi.payload.request;

import lombok.Data;

import java.util.Set;

@Data
public class RoleRequest {
    Long id;
    String name;
    Long userId;
    Set<RoleFeatures> roleFeatures;
    Set<Long> roles;
}