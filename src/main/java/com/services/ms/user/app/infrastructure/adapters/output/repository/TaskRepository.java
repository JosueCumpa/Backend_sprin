package com.services.ms.user.app.infrastructure.adapters.output.repository;

import com.services.ms.user.app.infrastructure.adapters.output.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByUserId(Long userId);
}
