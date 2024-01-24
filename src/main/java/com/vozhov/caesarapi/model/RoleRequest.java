package com.vozhov.caesarapi.model;

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