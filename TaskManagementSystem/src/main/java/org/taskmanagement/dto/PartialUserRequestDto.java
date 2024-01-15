package org.taskmanagement.dto;

import lombok.Data;

@Data
public class PartialUserRequestDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
