package com.services.ms.user.app.infrastructure.adapters.output;

import com.services.ms.user.app.application.ports.output.TaskPersistence;
import com.services.ms.user.app.domain.model.Task;
import com.services.ms.user.app.infrastructure.adapters.output.mapper.TaskPersistenceMapper;
import com.services.ms.user.app.infrastructure.adapters.output.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskPersistenceAdapter implements TaskPersistence {

    private final TaskRepository repository;
    private final TaskPersistenceMapper mapper;

    @Override
    public Optional<Task> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public List<Task> findAll() {
        return mapper.toTaskList(repository.findAll());
    }

    @Override
    public List<Task> findByUserId(Long userId) {
        return mapper.toTaskList(repository.findByUserId(userId));
    }

    @Override
    public Task save(Task task) {
        return mapper.toModel(repository.save(mapper.toEntity(task)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
