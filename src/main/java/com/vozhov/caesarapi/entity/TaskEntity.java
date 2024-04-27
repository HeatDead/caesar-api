package com.vozhov.caesarapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @Size(max = 2048)
    private String description;

    private Date startDate;
    private Date deadline;

    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private UserEntity assignee;

    @ManyToOne
    private ProjectEntity projectEntity;
}