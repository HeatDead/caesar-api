package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.RoleEntity;
import com.vozhov.caesarapi.payload.request.user.Permission;
import com.vozhov.caesarapi.payload.request.user.RoleRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void createRole(String name);
    void editRole(RoleRequest roleRequest);
    List<RoleEntity> getRoles();
    RoleEntity getRole(Long id);

    ResponseEntity<?> deleteRole(Long id);
}
