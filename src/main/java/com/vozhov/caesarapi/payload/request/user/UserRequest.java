package com.vozhov.caesarapi.payload.request.user;

import lombok.Data;

@Data
public class UserRequest {
    String name;
    String surname;
    String patronymic;
    String login;
    String password;
}