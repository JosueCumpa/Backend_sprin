package com.services.ms.user.app.infrastructure.adapters.output.repository;

import com.services.ms.user.app.infrastructure.adapters.output.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
