package com.vozhov.caesarapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "panels")
public class PanelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;

    @ManyToOne
    private DeskEntity deskEntity;

    private Long deskId;

    private TaskStatus status;

    @ManyToMany
    @JoinTable(name = "panels_tasks",
            joinColumns = @JoinColumn(name = "panel_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<TaskEntity> tasks;

    public void addTask(TaskEntity taskEntity) {
        if(!tasks.contains(taskEntity))
            tasks.add(taskEntity);
    }
    public void removeTask(TaskEntity taskEntity) {
        tasks.remove(taskEntity);
    }
}