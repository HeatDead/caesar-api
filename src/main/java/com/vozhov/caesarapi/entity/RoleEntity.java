package com.vozhov.caesarapi.entity;

import com.vozhov.caesarapi.model.RoleFeatures;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection(targetClass = RoleFeatures.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role_features", joinColumns = @JoinColumn(name = "role_id"))
    @Enumerated(EnumType.STRING)
    private Set<RoleFeatures> roleFeatures;
}
