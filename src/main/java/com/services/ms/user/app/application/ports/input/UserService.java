package com.services.ms.user.app.application.ports.input;

import com.services.ms.user.app.domain.model.User;

import java.util.List;

public interface UserService {
   //Buscar por id
    User findById(Long id);
    //Listar todos los usuarios
    List<User> findAll();
    //Guardar al usuario
    User save(User user);
    //actualizar
    User update(Long id , User user);
    // eliminar y activar o desactivar estado.
    void deleteById(Long id);

}
