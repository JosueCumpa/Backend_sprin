package com.services.ms.user.app;

import com.services.ms.user.app.infrastructure.adapters.output.entity.TaskEntity;
import com.services.ms.user.app.infrastructure.adapters.output.entity.UserEntity;
import com.services.ms.user.app.infrastructure.adapters.output.repository.TaskRepository;
import com.services.ms.user.app.infrastructure.adapters.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
public class UserServiceApplication implements CommandLineRunner {

	private  final UserRepository repository;
	private final TaskRepository taskRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception{
		if (repository.count() == 0) { // Solo insertar si la tabla está vacía
			List<UserEntity> entities = Arrays.asList(
					new UserEntity(null, "Juan", "Perez", 28, LocalDateTime.now(), LocalDateTime.now(), true),
					new UserEntity(null, "María", "Gómez", 25, LocalDateTime.now(), LocalDateTime.now(), false),
					new UserEntity(null, "Carlos", "López", 40, LocalDateTime.now(), LocalDateTime.now(), true),
					new UserEntity(null, "Ana", "Fernández", 35, LocalDateTime.now(), LocalDateTime.now(), false),
					new UserEntity(null, "Pedro", "Ramírez", 28, LocalDateTime.now(), LocalDateTime.now(), true)
			);
			repository.saveAll(entities);
			System.out.println("Datos de prueba insertados.");
		} else {
			System.out.println("La base de datos ya contiene datos. No se insertará nuevamente.");
		}

		if (taskRepository.count() == 0) { // Solo insertar si la tabla está vacía
				List<TaskEntity> tasks = List.of(
						new TaskEntity(null, "Completar informe mensual", "Realizar el informe financiero de febrero", true, LocalDateTime.now(), LocalDateTime.now(), 1L),
						new TaskEntity(null, "Preparar presentación", "Diseñar las diapositivas para la reunión del viernes", false, LocalDateTime.now(), LocalDateTime.now(), 2L)
				);
				taskRepository.saveAll(tasks);
				System.out.println("Datos de prueba insertados en la tabla de tareas.");

		} else {
			System.out.println("La base de datos ya contiene datos en 'tasks'. No se insertará nuevamente.");
		}
	}



}
