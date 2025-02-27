package com.services.ms.user.app.infrastructure.adapters.input.rest.mapper;

import com.services.ms.user.app.domain.model.Task;
import com.services.ms.user.app.domain.model.User;
import com.services.ms.user.app.infrastructure.adapters.input.rest.model.request.TaskCreateRequest;
import com.services.ms.user.app.infrastructure.adapters.input.rest.model.response.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskRestMapper {
    Task toTask(TaskCreateRequest request);

    TaskResponse toTaskResponse(Task task);

    List<TaskResponse> toTaskResponseList(List<Task> tasks);

}

