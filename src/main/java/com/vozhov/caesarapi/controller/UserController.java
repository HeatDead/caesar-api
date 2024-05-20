package com.vozhov.caesarapi.controller;

import com.vozhov.caesarapi.entity.UserEntity;
import com.vozhov.caesarapi.payload.request.auth.RegisterRequest;
import com.vozhov.caesarapi.payload.request.user.AddUserToGroupRequest;
import com.vozhov.caesarapi.payload.request.user.RoleRequest;
import com.vozhov.caesarapi.payload.request.user.UserRequest;
import com.vozhov.caesarapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @PostMapping("/role")
    @PreAuthorize("hasAuthority('administrator')")
    public void addRole(@RequestBody RoleRequest roleRequest) {
        userService.addRole(roleRequest.getUserId(), roleRequest.getRole());
    }

    @GetMapping("/details")
    public UserEntity getMyDetails(@RequestHeader String authorization) {
        return userService.getMyDetails(authorization);
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('administrator')")
    public void editUser(@RequestBody RegisterRequest request) {
        userService.editUser(request);
    }

    @PostMapping("/password")
    @PreAuthorize("hasAuthority('administrator')")
    public void editPassword(@RequestBody RegisterRequest request) {
        userService.editPassword(request);
    }

    @PostMapping("/block")
    @PreAuthorize("hasAuthority('administrator')")
    public void blockUser(@RequestParam String username) {
        userService.blockUser(username);
    }

    @PostMapping("/unblock")
    @PreAuthorize("hasAuthority('administrator')")
    public void unblockUser(@RequestParam String username) {
        userService.unblockUser(username);
    }

    @GetMapping("/get")
    public UserEntity getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('administrator')")
    public void createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest.getName(), userRequest.getSurname(), userRequest.getPatronymic(), userRequest.getLogin(), userRequest.getPassword());
    }

    @PostMapping("/group")
    @PreAuthorize("hasAuthority('administrator')")
    public void addGroup(@RequestBody AddUserToGroupRequest request) {
        userService.addGroup(request.getUserId(), request.getGroupId());
    }

    @GetMapping
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }
}