package com.services.ms.user.app.application.service;

import com.services.ms.user.app.application.ports.input.TaskService;
import com.services.ms.user.app.application.ports.output.TaskPersistence;
import com.services.ms.user.app.application.ports.output.UserPersistence;
import com.services.ms.user.app.domain.exception.TaskNotFoundException;
import com.services.ms.user.app.domain.model.Task;
import com.services.ms.user.app.infrastructure.adapters.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskPersistence persistence;
    private final UserPersistence persistenceuser;
    @Override
    public Task findById(Long id) {
        return persistence.findById(id).orElseThrow(TaskNotFoundException::new);
    }

    @Override
    public List<Task> findByUserId(Long userId) {
        return persistence.findByUserId(userId);
    }

    @Override
    public List<Task> findAll() {
        return persistence.findAll();
    }

    @Override
    public Task save(Task task) {
            if (task.getUserId() != null) {
                // Validar si el usuario existe en la base de datos
                var userOptional = persistenceuser.findById(task.getUserId());
                if (userOptional.isEmpty()) {
                    throw new RuntimeException("El usuario con ID " + task.getUserId() + " no existe.");
                }
            }

            // Asignar timestamps automáticos
            task.setCreatedAt(LocalDateTime.now());
            task.setUpdatedAt(LocalDateTime.now());

            return persistence.save(task);
    }



    @Override
    public Task update(Long id, Task task) {
        return persistence.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(task.getTitle());
                    existingTask.setDescription(task.getDescription());
                    existingTask.setCompleted(task.isCompleted());
                    existingTask.setUpdatedAt(LocalDateTime.now());

                    // Validar si se quiere actualizar el usuario
                    if (task.getUserId() != null) {
                        var userOptional = persistenceuser.findById(task.getUserId());
                        if (userOptional.isEmpty()) {
                            throw new RuntimeException("El usuario con ID " + task.getUserId() + " no existe.");
                        }
                        existingTask.setUserId(task.getUserId()); // ✅ Se guarda solo el ID
                    }

                    return persistence.save(existingTask);
                })
                .orElseThrow(TaskNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if (persistence.findById(id).isEmpty()) {
            throw new TaskNotFoundException();
        }
        persistence.deleteById(id);
    }
}
