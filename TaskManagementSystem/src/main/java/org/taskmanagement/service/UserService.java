package org.taskmanagement.service;

import org.taskmanagement.dto.PartialUserRequestDto;
import org.taskmanagement.dto.UserRegistrationRequest;
import org.taskmanagement.dto.UserResponseDto;
import org.taskmanagement.model.User;

public interface UserService {
    UserResponseDto registerUser(UserRegistrationRequest request);

    void updateUserRole(Long userId, User.Role role);

    UserResponseDto getProfileInfo(String username);

    User getById(Long id);

    UserResponseDto updateByUsername(String username, PartialUserRequestDto dto);
}
