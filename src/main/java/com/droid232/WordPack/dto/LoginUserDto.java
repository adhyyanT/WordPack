package com.droid232.WordPack.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern.Flag;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDto {

    @Email(message = "The email address is invalid.", flags = { Flag.CASE_INSENSITIVE })
    @NotBlank(message = "The email address is required.")
    private String email;

    @NotEmpty(message = "The password name is required.")
    @Size(min = 2, max = 14, message = "The length of password must be between 2 and 14 characters.")
    private String password;
}
