package com.services.ms.user.app.application.ports.output;

import com.services.ms.user.app.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistence {
    Optional<User> findById(Long id);

    List<User> findAll();

    User save(User user);

    void deleteById(Long id);

}
