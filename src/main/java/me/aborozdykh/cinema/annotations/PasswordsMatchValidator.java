package me.aborozdykh.cinema.annotations;

import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import me.aborozdykh.cinema.models.dto.UserRegistrationDto;

/**
 * @author Andrii Borozdykh
 */
public class PasswordsMatchValidator
        implements ConstraintValidator<PasswordsMatch, UserRegistrationDto> {

    public boolean isValid(UserRegistrationDto userRegistrationDto,
                           ConstraintValidatorContext context) {
        return Objects.equals(userRegistrationDto.getPassword(),
                userRegistrationDto.getRepeatPassword());
    }
}
