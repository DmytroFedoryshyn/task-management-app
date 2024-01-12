package org.taskmanagement.service;


import org.taskmanagement.dto.UserRegistrationRequest;
import org.taskmanagement.dto.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserRegistrationRequest request);
}
