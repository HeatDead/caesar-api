package com.vozhov.caesarapi.repository;

import com.vozhov.caesarapi.entity.DeskEntity;
import com.vozhov.caesarapi.entity.PanelEntity;
import com.vozhov.caesarapi.entity.ProjectEntity;
import com.vozhov.caesarapi.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DeskRepository extends JpaRepository<DeskEntity, Long> {
    List<DeskEntity> findAllByProjectEntity(ProjectEntity projectEntity);
    Optional<DeskEntity> findByPanelsIsContaining(PanelEntity panel);
}
