package com.services.ms.user.app.application.ports.output;

import com.services.ms.user.app.domain.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskPersistence {
    Optional<Task> findById(Long id);
    List<Task> findAll();
    List<Task> findByUserId(Long userId);
    Task save(Task task);
    void deleteById(Long id);
}
