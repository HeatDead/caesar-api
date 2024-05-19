package com.vozhov.caesarapi.payload.request.auth;

import com.vozhov.caesarapi.entity.GroupEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String name;
    private String surname;
    private String patronymic;
    private String password;
    private List<Long> groups;
    private Long role;
}
