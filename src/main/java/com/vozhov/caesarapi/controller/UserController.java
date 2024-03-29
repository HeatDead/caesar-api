package com.vozhov.caesarapi.controller;

import com.vozhov.caesarapi.entity.UserEntity;
import com.vozhov.caesarapi.payload.request.user.AddUserToGroupRequest;
import com.vozhov.caesarapi.payload.request.user.RoleRequest;
import com.vozhov.caesarapi.payload.request.user.UserRequest;
import com.vozhov.caesarapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @PostMapping("/role")
    public void addRole(@RequestBody RoleRequest roleRequest) {
        userService.addRole(roleRequest.getUserId(), roleRequest.getRole());
    }

    @PostMapping
    public void createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest.getName(), userRequest.getSurname(), userRequest.getPatronymic(), userRequest.getLogin(), userRequest.getPassword());
    }

    @PostMapping("/group")
    public void addGroup(@RequestBody AddUserToGroupRequest request) {
        userService.addGroup(request.getUserId(), request.getGroupId());
    }

    @GetMapping
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }
}