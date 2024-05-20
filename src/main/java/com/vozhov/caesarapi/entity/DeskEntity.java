package com.vozhov.caesarapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "desks")
public class DeskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name = "projectEntity_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProjectEntity projectEntity;

    @ElementCollection
    @CollectionTable(name="panel_desk",
            joinColumns=@JoinColumn(name="panel_id"))
    @MapKeyColumn(name="panel_key")
    @Column(name="desk_value")
    private Map<TaskStatus, PanelEntity> panels = new LinkedHashMap<>();

    public void addPanel(PanelEntity panel) {
        panels.put(panel.getStatus(), panel);
    }

    public void deletePanel(TaskStatus status) {
        panels.remove(status);
    }
}