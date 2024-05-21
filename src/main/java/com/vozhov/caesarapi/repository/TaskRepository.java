package com.vozhov.caesarapi.repository;

import com.vozhov.caesarapi.entity.ProjectEntity;
import com.vozhov.caesarapi.entity.TaskEntity;
import com.vozhov.caesarapi.entity.TaskStatus;
import com.vozhov.caesarapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findAllByProjectEntity(ProjectEntity projectEntity);
    List<TaskEntity> findAllByProjectEntityAndStatus(ProjectEntity projectEntity, TaskStatus status);
    List<TaskEntity> findAllByAssignee(UserEntity userEntity);
    List<TaskEntity> findAllByAssigneeAndStatus(UserEntity assignee, TaskStatus status);
    List<TaskEntity> findAllByAssigneeAndFinishedDateBetween(UserEntity assignee, Date finishedDate, Date finishedDate2);
    List<TaskEntity> findAllByFinishedDate(Date finishedDate);
    //spring.jpa.hibernate.ddl-auto=create-drop
}
