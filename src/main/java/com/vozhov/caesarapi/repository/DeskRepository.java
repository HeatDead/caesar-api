package com.vozhov.caesarapi.repository;

import com.vozhov.caesarapi.entity.DeskEntity;
import com.vozhov.caesarapi.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeskRepository extends JpaRepository<DeskEntity, Long> {
    List<DeskEntity> findAllByProjectEntity(ProjectEntity projectEntity);
}
