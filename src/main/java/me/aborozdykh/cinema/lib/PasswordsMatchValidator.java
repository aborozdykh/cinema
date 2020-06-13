package me.aborozdykh.cinema.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Andrii Borozdykh
 */
public class PasswordsMatchValidator
        implements ConstraintValidator<PasswordsMatch, Object> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.repeatPassword = constraintAnnotation.repeatPassword();
    }

    public boolean isValid(Object value,
                           ConstraintValidatorContext context) {

        Object field = new BeanWrapperImpl(value)
                .getPropertyValue(this.password);
        Object fieldMatch = new BeanWrapperImpl(value)
                .getPropertyValue(this.repeatPassword);

        if (field != null) {
            return field.equals(fieldMatch);
        } else {
            return fieldMatch == null;
        }
    }
}
