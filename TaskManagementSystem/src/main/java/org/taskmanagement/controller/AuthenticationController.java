package org.taskmanagement.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taskmanagement.dto.UserLoginRequestDto;
import org.taskmanagement.dto.UserLoginResponseDto;
import org.taskmanagement.dto.UserRegistrationRequest;
import org.taskmanagement.dto.UserResponseDto;
import org.taskmanagement.exception.RegistrationException;
import org.taskmanagement.security.AuthenticationService;
import org.taskmanagement.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller", description = "Endpoints for managing authentication")

public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Operation(summary = "User login")
    @PostMapping("/login")
    public UserLoginResponseDto login(@Valid @RequestBody UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }

    @Operation(summary = "User registration")
    @PostMapping("/register")
    public UserResponseDto register(@Valid @RequestBody UserRegistrationRequest request)
            throws RegistrationException {
        return userService.registerUser(request);
    }
}

