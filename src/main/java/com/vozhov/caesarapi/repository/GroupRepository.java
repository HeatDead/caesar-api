package com.vozhov.caesarapi.repository;

import com.vozhov.caesarapi.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
}
