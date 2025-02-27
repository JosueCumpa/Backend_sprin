package com.services.ms.user.app.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private Integer age;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private boolean active;

}
