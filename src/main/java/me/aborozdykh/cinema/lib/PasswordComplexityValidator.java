package me.aborozdykh.cinema.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Andrii Borozdykh
 */
public class PasswordComplexityValidator implements
        ConstraintValidator<PasswordComplexityConstraint, String> {
    @Override
    public void initialize(PasswordComplexityConstraint constraintAnnotation) {
        // Do nothing because there is no fields in this class
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField
                .matches("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
    }
}


