package com.services.ms.user.app.infrastructure.adapters.input.rest.model.response;

import lombok.*;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String firstname;
    private String lastname;
    private Integer age;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private boolean active;
}
