package com.services.ms.user.app.infrastructure.adapters.input.rest;


import com.services.ms.user.app.application.ports.input.TaskService;
import com.services.ms.user.app.infrastructure.adapters.input.rest.mapper.TaskRestMapper;
import com.services.ms.user.app.infrastructure.adapters.input.rest.model.request.TaskCreateRequest;
import com.services.ms.user.app.infrastructure.adapters.input.rest.model.response.TaskResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskResAdapter {
    private final TaskService taskService;
    private final TaskRestMapper restMapper;

    @GetMapping("/v1/api")
    public List<TaskResponse> findAll() {
        return restMapper.toTaskResponseList(taskService.findAll());
    }

    // Obtener una tarea por ID
    @GetMapping("/v1/api/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(restMapper.toTaskResponse(taskService.findById(id)));
    }

    // Obtener todas las tareas de un usuario
    @GetMapping("/v1/api/user/{userId}")
    public List<TaskResponse> findByUserId(@PathVariable Long userId) {
        return restMapper.toTaskResponseList(taskService.findByUserId(userId));
    }

    // Crear una nueva tarea
    @PostMapping("/v1/api")
    public ResponseEntity<TaskResponse> save(@Valid @RequestBody TaskCreateRequest request) {
        System.out.println("📌 Título: " + request.getTitle());
        System.out.println("📌 Descripción: " + request.getDescription());
        System.out.println("📌 Completado: " + request.isCompleted());
        System.out.println("📌 userId: " + request.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restMapper.toTaskResponse(taskService.save(restMapper.toTask(request))));
    }

    // Actualizar una tarea
    @PutMapping("/v1/api/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, @Valid @RequestBody TaskCreateRequest request) {
        return ResponseEntity.ok(restMapper.toTaskResponse(taskService.update(id, restMapper.toTask(request))));
    }

    // Eliminar una tarea
    @DeleteMapping("/v1/api/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
