package com.services.ms.user.app.infrastructure.adapters.input.rest.mapper;

import com.services.ms.user.app.domain.model.User;
import com.services.ms.user.app.infrastructure.adapters.input.rest.model.request.UserCreateRequest;
import com.services.ms.user.app.infrastructure.adapters.input.rest.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRestMapper {

    User toUser(UserCreateRequest request);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> userList);
}

