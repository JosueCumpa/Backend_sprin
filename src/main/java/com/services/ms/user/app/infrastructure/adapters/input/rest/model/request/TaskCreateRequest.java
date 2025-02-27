package com.services.ms.user.app.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateRequest {
    @NotBlank(message = "Title cannot be empty")
    private String title;
    private String description;
    private boolean completed;
    private Long userId;
}
