package org.taskmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.taskmanagement.validation.PasswordsMatch;

@Data
@PasswordsMatch
public class UserRegistrationRequest {
    @NotBlank
    @Size(min = 3, max = 100)
    private String username;
    @Email
    private String email;
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
    private String repeatPassword;
    @NotBlank
    @Size(min = 3, max = 100)
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 100)
    private String lastName;
}
