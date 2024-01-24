package com.vozhov.caesarapi.controller;

import com.vozhov.caesarapi.entity.UserEntity;
import com.vozhov.caesarapi.model.RoleRequest;
import com.vozhov.caesarapi.model.UserRequest;
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
        userService.addRole(roleRequest.getUserId(), roleRequest.getRoles());
    }

    @PostMapping
    public void createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest.getName(), userRequest.getSurname(), userRequest.getPatronymic(), userRequest.getLogin(), userRequest.getPassword());
    }

    @GetMapping
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }
}