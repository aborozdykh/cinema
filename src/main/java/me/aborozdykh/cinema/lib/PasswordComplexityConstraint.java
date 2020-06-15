package me.aborozdykh.cinema.lib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PasswordComplexityValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordComplexityConstraint {
    String message() default "Password must be more than 8 symbols, "
            + "must contains A-Z, a-z, 0-9 and special characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
