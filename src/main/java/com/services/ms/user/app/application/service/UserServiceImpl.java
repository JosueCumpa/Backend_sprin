package com.services.ms.user.app.application.service;

import com.services.ms.user.app.application.ports.input.UserService;
import com.services.ms.user.app.application.ports.output.UserPersistence;
import com.services.ms.user.app.domain.exception.UserNotFountException;
import com.services.ms.user.app.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserPersistence persistence;


    @Override
    public User findById(Long id) {
        return persistence.findById(id).orElseThrow(UserNotFountException::new);
    }

    @Override
    public List<User> findAll() {
        return persistence.findAll();
    }

    @Override
    public User save(User user) {
        return persistence.save(user);
    }

    @Override
    public User update(Long id, User user) {
        return persistence.findById(id)
                .map(savedUser ->{
                    savedUser.setFirstname(user.getFirstname());
                    savedUser.setLastname(user.getLastname());
                    savedUser.setAge(user.getAge());
                    savedUser.setUpdateAt(java.time.LocalDateTime.now());
                    savedUser.setActive(user.isActive());
                    return persistence.save(savedUser);
                })
                .orElseThrow(UserNotFountException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(persistence.findById(id).isEmpty()){
            throw new UserNotFountException();
        }
        persistence.deleteById(id);
    }

}
