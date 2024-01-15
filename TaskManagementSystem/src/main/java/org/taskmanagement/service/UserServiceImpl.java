package org.taskmanagement.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taskmanagement.dto.PartialUserRequestDto;
import org.taskmanagement.dto.UserRegistrationRequest;
import org.taskmanagement.dto.UserResponseDto;
import org.taskmanagement.exception.EntityNotFoundException;
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

    @Override
    public void updateUserRole(Long userId, User.Role role) {
        repository.updateRoleById(userId, role);
    }

    @Override
    public UserResponseDto getProfileInfo(String username) {
        Optional<User> userOptional = repository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("Profile with username: "
                    + username + " not found.");
        }

        return userMapper.toUserDto(userOptional.get());
    }

    @Override
    public User getById(Long id) {
        Optional<User> userOptional = repository.findById(id);

        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with id: " + id + " not found");
        }

        return userOptional.get();
    }

    @Override
    public UserResponseDto updateByUsername(String username, PartialUserRequestDto dto) {
        Optional<User> userOptional = repository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with username: "
                    + username + " not found.");
        }

        User user = userOptional.get();
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }

        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }

        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }

        return userMapper.toUserDto(repository.save(user));
    }
}
