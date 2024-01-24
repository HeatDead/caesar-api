package com.vozhov.caesarapi.entity;

import com.vozhov.caesarapi.model.RoleFeatures;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "usr")
public class UserEntity {
    @Id
    private String login;

    private String password;

    private String name;
    private String surname;
    private String patronymic;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Long> roles = new HashSet<>();
}