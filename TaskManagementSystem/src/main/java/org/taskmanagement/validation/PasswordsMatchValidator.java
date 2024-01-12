package org.taskmanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import org.taskmanagement.dto.UserRegistrationRequest;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof UserRegistrationRequest) {
            UserRegistrationRequest userRequestDto = (UserRegistrationRequest) value;
            return Objects.equals(userRequestDto.getPassword(), userRequestDto.getRepeatPassword());
        }
        return false;
    }
}
