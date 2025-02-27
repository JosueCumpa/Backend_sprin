package com.services.ms.user.app.infrastructure.adapters.output.mapper;

import com.services.ms.user.app.domain.model.Task;
import com.services.ms.user.app.infrastructure.adapters.output.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskPersistenceMapper {
    TaskEntity toEntity(Task task);

    Task toModel(TaskEntity entity);

    List<Task> toTaskList(List<TaskEntity> entities);
}
