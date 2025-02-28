package com.services.ms.user.app.infrastructure.adapters.input.rest;


import com.services.ms.user.app.application.ports.input.TaskService;
import com.services.ms.user.app.infrastructure.adapters.input.rest.mapper.TaskRestMapper;
import com.services.ms.user.app.infrastructure.adapters.input.rest.model.request.TaskCreateRequest;
import com.services.ms.user.app.infrastructure.adapters.input.rest.model.response.TaskResponse;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
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
//@Tag(name = "Task Controller", description = "Operaciones relacionadas con las tareas") // <-- DOCUMENTA EL CONTROLADOR
public class TaskResAdapter {
    private final TaskService taskService;
    private final TaskRestMapper restMapper;

    //@Operation(summary = "Listar todas las tareas", description = "Obtiene todas las tareas registradas en el sistema")
    //@ApiResponse(responseCode = "200", description = "Lista de tareas obtenida con éxito")
    @GetMapping("/v1/api")
    public List<TaskResponse> findAll() {
        return restMapper.toTaskResponseList(taskService.findAll());
    }

    // Obtener una tarea por ID
    //@Operation(summary = "Obtener una tarea por ID", description = "Obtiene una tarea específica a partir de su ID")
    //@ApiResponse(responseCode = "200", description = "Tarea obtenida con éxito")
    //@ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    @GetMapping("/v1/api/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(restMapper.toTaskResponse(taskService.findById(id)));
    }

    // Obtener todas las tareas de un usuario
    //@Operation(summary = "Listar tareas de un usuario", description = "Obtiene todas las tareas asignadas a un usuario")
    //@ApiResponse(responseCode = "200", description = "Lista de tareas obtenida con éxito")
    //@GetMapping("/v1/api/user/{userId}")
    public List<TaskResponse> findByUserId(@PathVariable Long userId) {
        return restMapper.toTaskResponseList(taskService.findByUserId(userId));
    }

    // Crear una nueva tarea
    //@Operation(summary = "Crear una nueva tarea", description = "Registra una nueva tarea en el sistema")
    //@ApiResponse(responseCode = "201", description = "Tarea creada con éxito")
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
    //@Operation(summary = "Actualizar una tarea", description = "Modifica una tarea existente en el sistema")
    //@ApiResponse(responseCode = "200", description = "Tarea actualizada con éxito")
    //@ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    @PutMapping("/v1/api/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, @Valid @RequestBody TaskCreateRequest request) {
        return ResponseEntity.ok(restMapper.toTaskResponse(taskService.update(id, restMapper.toTask(request))));
    }

    // Eliminar una tarea
    //@Operation(summary = "Eliminar una tarea", description = "Elimina una tarea específica a partir de su ID")
    //@ApiResponse(responseCode = "204", description = "Tarea eliminada con éxito")
    //@ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    @DeleteMapping("/v1/api/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
