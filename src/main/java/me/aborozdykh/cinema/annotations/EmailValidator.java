package me.aborozdykh.cinema.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Andrii Borozdykh
 */
public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    private static final String EMAIL_REGEXP = "^.+@.*";

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext constraintValidatorContext) {
        return contactField != null
                && contactField.matches(EMAIL_REGEXP);
    }
}
