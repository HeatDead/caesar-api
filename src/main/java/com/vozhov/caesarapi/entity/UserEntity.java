package com.vozhov.caesarapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(max = 20, min = 4)
    private String login;

    @NotBlank
    @Size(max = 120, min = 6)
    private String password;

    @NotBlank
    @Size(min = 1)
    private String name;
    @NotBlank
    @Size(min = 1)
    private String surname;
    @Size(min = 1)
    private String patronymic;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Long> roles = new HashSet<>();

    @ManyToMany
    private Set<GroupEntity> groups = new HashSet<>();
}