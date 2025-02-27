package com.services.ms.user.app.infrastructure.adapters.input.rest;

import com.services.ms.user.app.application.ports.input.UserService;
import com.services.ms.user.app.domain.model.User;
import com.services.ms.user.app.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import com.services.ms.user.app.infrastructure.adapters.input.rest.model.request.UserCreateRequest;
import com.services.ms.user.app.infrastructure.adapters.input.rest.model.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserResAdapter {
    private final UserService userService;
    private final UserRestMapper restMapper;

    @GetMapping("/v1/api")
    public List<UserResponse> findAll(){
        return restMapper.toUserResponseList(userService.findAll());
    }

    @GetMapping("/v1/api/{id}")
    public UserResponse findByid(@PathVariable Long id){
        return restMapper.toUserResponse(userService.findById(id));
    }

    @PostMapping("/v1/api")
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restMapper.toUserResponse(
                        userService.save(restMapper.toUser(request)))
                );
    }

    @PutMapping("/v1/api/{id}")
    public UserResponse update(@PathVariable Long id, @Valid @RequestBody UserCreateRequest request){
        return restMapper.toUserResponse(
                userService.update(id, restMapper.toUser(request))
        );
    }

    @DeleteMapping("/v1/api/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

}
