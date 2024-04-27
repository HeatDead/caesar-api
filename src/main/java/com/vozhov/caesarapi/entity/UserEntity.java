package com.vozhov.caesarapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@Table(name = "usr")
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @NotBlank
    @Size(max = 20, min = 4)
    private String username;

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

    @ManyToOne
    private RoleEntity role;

    @ManyToMany
    private Set<GroupEntity> groups = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}