package com.vozhov.caesarapi.controller;

import com.vozhov.caesarapi.payload.request.RoleRequest;
import com.vozhov.caesarapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@CrossOrigin
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public void createRole(@RequestBody RoleRequest roleRequest) {
        roleService.createRole(roleRequest.getName(), roleRequest.getRoleFeatures());
    }
}
