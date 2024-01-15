package org.taskmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.taskmanagement.config.MapperConfig;
import org.taskmanagement.dto.UserLoginRequestDto;
import org.taskmanagement.dto.UserRegistrationRequest;
import org.taskmanagement.dto.UserRequestDto;
import org.taskmanagement.dto.UserResponseDto;
import org.taskmanagement.model.User;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toUser(UserRegistrationRequest userRegistrationRequest);

    @Mapping(target = "id", ignore = true)
    User toUser(UserLoginRequestDto dto);

    UserResponseDto toUserDto(User user);

    @Mapping(target = "id", ignore = true)
    User fromUserRequestDto(UserRequestDto requestDto);
}
