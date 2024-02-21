package com.vozhov.caesarapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class PanelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;

    @ManyToOne
    private DeskEntity deskEntity;

    @ManyToMany
    private List<TaskEntity> tasks;

    public void addTask(TaskEntity taskEntity) {
        if(!tasks.contains(taskEntity))
            tasks.add(taskEntity);
    }
    public void removeTask(TaskEntity taskEntity) {
        tasks.remove(taskEntity);
    }
}