package com.vozhov.caesarapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    private TaskStatus status;
    private TaskType type;
    private TaskPriority priority;

    private Date startDate;
    private Date deadline;
    @Temporal(TemporalType.DATE)
    private Date finishedDate;

    private int difficulty = 0;

    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private UserEntity assignee;
    @ManyToOne
    private GroupEntity group;

    @ManyToOne
    @JoinColumn(name = "projectEntity_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProjectEntity projectEntity;
}