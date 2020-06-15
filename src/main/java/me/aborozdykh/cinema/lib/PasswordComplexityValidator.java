package me.aborozdykh.cinema.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Andrii Borozdykh
 */
public class PasswordComplexityValidator implements
        ConstraintValidator<PasswordComplexityConstraint, String> {
    private final String passwordRegexp
            = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField
                .matches(passwordRegexp);
    }
}


