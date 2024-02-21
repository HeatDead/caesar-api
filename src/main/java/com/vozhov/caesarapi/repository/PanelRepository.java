package com.vozhov.caesarapi.repository;

import com.vozhov.caesarapi.entity.DeskEntity;
import com.vozhov.caesarapi.entity.PanelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PanelRepository extends JpaRepository<PanelEntity, Long> {
    List<PanelEntity> findAllByDeskEntity(DeskEntity deskEntity);
}
