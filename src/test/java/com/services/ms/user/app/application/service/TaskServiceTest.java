package com.services.ms.user.app.application.service;

import com.services.ms.user.app.application.ports.input.TaskService;
import com.services.ms.user.app.application.ports.output.TaskPersistence;
import com.services.ms.user.app.application.ports.output.UserPersistence;
import com.services.ms.user.app.domain.exception.TaskNotFoundException;
import com.services.ms.user.app.domain.model.Task;
import com.services.ms.user.app.domain.model.User;
import com.services.ms.user.app.infrastructure.adapters.output.entity.TaskEntity;
import com.services.ms.user.app.infrastructure.adapters.output.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskPersistence taskPersistence;

    @Mock
    private UserPersistence userPersistence;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task(1L, "Test Task", "Description", false, LocalDateTime.now(), LocalDateTime.now(), 1L);
    }

    @Test
    void findById_ShouldReturnTask() {
        System.out.println("🔹 [Entrada] Buscando tarea con ID: 1");

        when(taskPersistence.findById(1L)).thenReturn(Optional.of(task));

        System.out.println("🔄 [Proceso] Ejecutando búsqueda...");
        Task result = taskService.findById(1L);

        System.out.println("✅ [Salida] Tarea encontrada: " + result);
        assertNotNull(result);
        assertEquals(task.getId(), result.getId());
        verify(taskPersistence, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowExceptionWhenNotFound() {
        System.out.println("🔹 [Entrada] Buscando tarea con ID: 2 (No existe)");

        when(taskPersistence.findById(2L)).thenReturn(Optional.empty());

        System.out.println("❌ [Error esperado] Lanzando TaskNotFoundException...");
        assertThrows(TaskNotFoundException.class, () -> taskService.findById(2L));

        verify(taskPersistence, times(1)).findById(2L);
    }

    @Test
    void findAll_ShouldReturnAllTasks() {
        System.out.println("🔹 [Entrada] Buscando todas las tareas...");

        List<Task> tasks = List.of(
                task, new Task(2L, "Task 2", "Desc 2", true, LocalDateTime.now(), LocalDateTime.now(), 2L)
        );
        when(taskPersistence.findAll()).thenReturn(tasks);

        System.out.println("🔄 [Proceso] Ejecutando búsqueda...");
        List<Task> result = taskService.findAll();

        System.out.println("✅ [Salida] Total de tareas encontradas: " + result.size());
        assertEquals(2, result.size());
        verify(taskPersistence, times(1)).findAll();
    }

    @Test
    void findByUserId_ShouldReturnTasks() {
        System.out.println("🔹 [Entrada] Buscando tareas del usuario con ID: 1");

        List<Task> tasks = List.of(task);
        when(taskPersistence.findByUserId(1L)).thenReturn(tasks);

        System.out.println("🔄 [Proceso] Ejecutando búsqueda...");
        List<Task> result = taskService.findByUserId(1L);

        System.out.println("✅ [Salida] Total de tareas encontradas: " + result.size());
        assertEquals(1, result.size());
        verify(taskPersistence, times(1)).findByUserId(1L);
    }

    @Test
    void save_ShouldReturnSavedTask() {
        System.out.println("🔹 [Entrada] Guardando nueva tarea...");

        when(userPersistence.findById(1L)).thenReturn(Optional.of(
                new User(1L, "John", "Doe", 30, LocalDateTime.now(), LocalDateTime.now(), true)
        ));
        when(taskPersistence.save(any(Task.class))).thenReturn(task);

        System.out.println("🔄 [Proceso] Ejecutando guardado...");
        Task result = taskService.save(task);

        System.out.println("✅ [Salida] Tarea guardada: " + result);
        assertNotNull(result);
        assertEquals("Test Task", result.getTitle());
        verify(taskPersistence, times(1)).save(any(Task.class));
    }

    @Test
    void save_ShouldThrowExceptionIfUserNotExists() {
        System.out.println("🔹 [Entrada] Guardando tarea para usuario inexistente (ID: 1)");

        when(userPersistence.findById(1L)).thenReturn(Optional.empty());

        System.out.println("❌ [Error esperado] Lanzando excepción...");
        assertThrows(RuntimeException.class, () -> taskService.save(task));

        verify(userPersistence, times(1)).findById(1L);
        verify(taskPersistence, never()).save(any(Task.class));
    }

    @Test
    void update_ShouldReturnUpdatedTask() {
        System.out.println("🔹 [Entrada] Actualizando tarea con ID: 1");

        when(taskPersistence.findById(1L)).thenReturn(Optional.of(task));
        when(userPersistence.findById(1L)).thenReturn(Optional.of(
                new User(1L, "John", "Doe", 30, LocalDateTime.now(), LocalDateTime.now(), true)
        ));
        when(taskPersistence.save(any(Task.class))).thenReturn(task);

        Task updatedTask = new Task(1L, "Updated Task", "Updated Desc", true, LocalDateTime.now(), LocalDateTime.now(), 1L);

        System.out.println("🔄 [Proceso] Ejecutando actualización...");
        Task result = taskService.update(1L, updatedTask);

        System.out.println("✅ [Salida] Tarea actualizada: " + result);
        assertNotNull(result);
        assertEquals("Updated Task", result.getTitle());
        verify(taskPersistence, times(1)).save(any(Task.class));
    }

    @Test
    void update_ShouldThrowExceptionIfNotFound() {
        System.out.println("🔹 [Entrada] Intentando actualizar tarea inexistente (ID: 1)");

        when(taskPersistence.findById(1L)).thenReturn(Optional.empty());

        Task updatedTask = new Task(1L, "Updated Task", "Updated Desc", true, LocalDateTime.now(), LocalDateTime.now(), 1L);

        System.out.println("❌ [Error esperado] Lanzando TaskNotFoundException...");
        assertThrows(TaskNotFoundException.class, () -> taskService.update(1L, updatedTask));

        verify(taskPersistence, never()).save(any(Task.class));
    }

    @Test
    void deleteById_ShouldDeleteTask() {
        System.out.println("🔹 [Entrada] Eliminando tarea con ID: 1");

        when(taskPersistence.findById(1L)).thenReturn(Optional.of(task));
        doNothing().when(taskPersistence).deleteById(1L);

        System.out.println("🔄 [Proceso] Ejecutando eliminación...");
        taskService.deleteById(1L);

        System.out.println("✅ [Salida] Tarea eliminada correctamente.");
        verify(taskPersistence, times(1)).deleteById(1L);
    }

    @Test
    void deleteById_ShouldThrowExceptionIfNotFound() {
        System.out.println("🔹 [Entrada] Intentando eliminar tarea inexistente (ID: 1)");

        when(taskPersistence.findById(1L)).thenReturn(Optional.empty());

        System.out.println("❌ [Error esperado] Lanzando TaskNotFoundException...");
        assertThrows(TaskNotFoundException.class, () -> taskService.deleteById(1L));

        verify(taskPersistence, never()).deleteById(anyLong());
    }
}
