package com.vozhov.caesarapi.entity;

import com.vozhov.caesarapi.payload.request.user.Permission;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ElementCollection(targetClass = Permission.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "permissions", joinColumns = @JoinColumn(name = "role_id"))
    @Enumerated(EnumType.STRING)
    private Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name));
        return authorities;
    }
}
