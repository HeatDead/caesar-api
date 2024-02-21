package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.payload.request.RoleFeatures;

import java.util.Set;

public interface RoleService {
    void createRole(String name, Set<RoleFeatures> roleFeatures);
}
