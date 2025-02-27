package com.services.ms.user.app.infrastructure.adapters.output;

import com.services.ms.user.app.application.ports.output.UserPersistence;
import com.services.ms.user.app.domain.exception.UserNotFountException;
import com.services.ms.user.app.domain.model.User;
import com.services.ms.user.app.infrastructure.adapters.output.mapper.UserPersistenceMapper;
import com.services.ms.user.app.infrastructure.adapters.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements UserPersistence {

    private final UserRepository repository;
    private final UserPersistenceMapper mapper;

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toUser);
    }

    @Override
    public List<User> findAll() {
        return mapper.toUserList(repository.findAll());
    }

    @Override
    public User save(User user) {
        return mapper.toUser(
                repository.save(mapper.toUserEntity(user)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
