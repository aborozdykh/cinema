package me.aborozdykh.cinema.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Andrii Borozdykh
 */
public class PasswordComplexityValidator implements
        ConstraintValidator<PasswordComplexityConstraint, String> {
    private static final String PASSWORD_REGEXP
            = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

    @Override
    public boolean isValid(String password,
                           ConstraintValidatorContext cxt) {
        return password != null
                && password.matches(PASSWORD_REGEXP);
    }
}


