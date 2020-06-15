package me.aborozdykh.cinema.lib;

import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import me.aborozdykh.cinema.models.dto.UserRegistrationDto;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Andrii Borozdykh
 */
public class PasswordsMatchValidator
        implements ConstraintValidator<PasswordsMatch, UserRegistrationDto> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.repeatPassword = constraintAnnotation.repeatPassword();
    }

    public boolean isValid(UserRegistrationDto value,
                           ConstraintValidatorContext context) {

        Object field = new BeanWrapperImpl(value)
                .getPropertyValue(this.password);
        Object fieldMatch = new BeanWrapperImpl(value)
                .getPropertyValue(this.repeatPassword);

        return Objects.equals(field, fieldMatch);
    }
}
