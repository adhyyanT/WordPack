package com.droid232.WordPack.dto;

import java.time.LocalDateTime;

import com.droid232.WordPack.entity.User;

import lombok.Data;

@Data
public class LoginResponseDto {
    private Long id;
    private String email;
    private String fullName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LoginResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        createdAt = user.getCreatedAt();
        updatedAt = user.getUpdatedAt();
        fullName = user.getFullName();
    }
}
