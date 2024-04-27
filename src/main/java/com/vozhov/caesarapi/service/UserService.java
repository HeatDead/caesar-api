package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.entity.RoleEntity;
import com.vozhov.caesarapi.entity.UserEntity;

import java.util.List;
import java.util.Set;

public interface UserService {
    void createUser(String name, String surname, String patronymic, String login, String password);

    void addRole(String userId, RoleEntity role);

    List<UserEntity> getUsers();

    void addGroup(String userId, Long groupId);
    UserEntity getMyDetails(String token);
    UserEntity getUserByUsername(String username);
}
