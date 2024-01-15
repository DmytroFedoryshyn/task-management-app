package org.taskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taskmanagement.dto.PartialUserRequestDto;
import org.taskmanagement.dto.UserResponseDto;
import org.taskmanagement.dto.UserRoleChangeDto;
import org.taskmanagement.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/role")
    public void updateRole(@PathVariable Long id,
                           @RequestBody UserRoleChangeDto role) {
        userService.updateUserRole(id, role.getRole());
    }

    @GetMapping("/me")
    public UserResponseDto profileInfo(Authentication authentication) {
        return userService.getProfileInfo(authentication.getName());
    }

    @PatchMapping("/me")
    public UserResponseDto updateProfileInfo(Authentication authentication,
                                             @RequestBody PartialUserRequestDto userRequestDto) {
        return userService.updateByUsername(authentication.getName(), userRequestDto);
    }
}
