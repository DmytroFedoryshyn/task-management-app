package org.taskmanagement.dto;

import lombok.Data;
import org.taskmanagement.model.User;

@Data
public class UserRoleChangeDto {
    private User.Role role;
}
