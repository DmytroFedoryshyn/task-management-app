package org.taskmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taskmanagement.dto.UserRegistrationRequest;
import org.taskmanagement.dto.UserResponseDto;
import org.taskmanagement.exception.RegistrationException;
import org.taskmanagement.mapper.UserMapper;
import org.taskmanagement.model.User;
import org.taskmanagement.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto registerUser(UserRegistrationRequest request) {
        if (repository.findByUsername(request.getUsername()).isPresent()
                || repository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(User.Role.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user = repository.save(user);

        return userMapper.toUserDto(user);
    }
}
