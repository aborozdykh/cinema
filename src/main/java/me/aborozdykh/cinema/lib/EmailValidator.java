package me.aborozdykh.cinema.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Andrii Borozdykh
 */
public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    @Override
    public void initialize(EmailConstraint constraintAnnotation) {
        // Do nothing because there is no fields in this class
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext constraintValidatorContext) {
        return contactField
                .matches("^.+@.*");
    }
}
