package com.services.ms.user.app.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    @NotBlank(message = "Field firstname cannot be empty or null.")
    private String firstname;
    @NotBlank(message = "Field lastname cannot be empty or null.")
    private String lastname;
    @NotNull(message = "Field age cannot be null")
    private Integer age;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private boolean active;
}
