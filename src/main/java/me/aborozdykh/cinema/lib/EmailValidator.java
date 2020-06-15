package me.aborozdykh.cinema.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Andrii Borozdykh
 */
public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    private final String emailRegexp = "^.+@.*";

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext constraintValidatorContext) {
        return contactField
                .matches(emailRegexp);
    }
}
