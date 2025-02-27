package com.services.ms.user.app.infrastructure.adapters.output.mapper;
import com.services.ms.user.app.domain.model.User;
import com.services.ms.user.app.infrastructure.adapters.output.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    UserEntity toUserEntity(User user);
    User toUser(UserEntity entity);
    List<User> toUserList(List<UserEntity> entityList);
}
