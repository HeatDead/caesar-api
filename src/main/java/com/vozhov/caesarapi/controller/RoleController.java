package com.vozhov.caesarapi.controller;

import com.vozhov.caesarapi.entity.RoleEntity;
import com.vozhov.caesarapi.payload.request.user.Permission;
import com.vozhov.caesarapi.payload.request.user.RoleRequest;
import com.vozhov.caesarapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@CrossOrigin
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    @PreAuthorize("hasAuthority('administrator')")
    public void createRole(@RequestBody RoleRequest roleRequest) {
        roleService.createRole(roleRequest.getName());
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('administrator')")
    public void editRole(@RequestBody RoleRequest roleRequest) {
        roleService.editRole(roleRequest);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('administrator')")
    public ResponseEntity<?> deleteRole(@RequestParam Long id) {
        return roleService.deleteRole(id);
    }

    @GetMapping("get")
    public RoleEntity getRole(@RequestParam Long id) {
        return roleService.getRole(id);
    }

    @GetMapping("/permissions")
    public List<String> getPermissions() {
        return Stream.of(Permission.values())
                .map(Permission::name)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<RoleEntity> getRoles() {
        return roleService.getRoles();
    }
}
