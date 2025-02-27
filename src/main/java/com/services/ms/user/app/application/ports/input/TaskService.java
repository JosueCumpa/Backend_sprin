package com.services.ms.user.app.application.ports.input;


import com.services.ms.user.app.domain.model.Task;

import java.util.List;

public interface TaskService {
    //Buscar por id
    Task findById(Long id);
    //Buscar por id Usuario
    List<Task> findByUserId(Long userId);
    //Listar todos los usuarios
    List<Task> findAll();
    //Guardar al usuario
    Task save(Task task);
    //actualizar
    Task update(Long id , Task task);
    // eliminar y activar o desactivar estado.
    void deleteById(Long id);

}
