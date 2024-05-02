package com.vozhov.caesarapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 120, min = 2)
    private String name;
    @Size(max = 4096)
    private String description;

    @OneToMany
    private List<UserEntity> employees;

    private ProjectStatus status;
    private Date startDate;
    private Date deadline;

    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private UserEntity responsible;

    public void addEmployee(UserEntity employee) {
        employees.add(employee);
    }

    public void clearEmployee() {
        employees.clear();
    }
}