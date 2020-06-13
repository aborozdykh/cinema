package me.aborozdykh.cinema.models.dto;

import javax.validation.constraints.NotNull;
import me.aborozdykh.cinema.lib.EmailConstraint;
import me.aborozdykh.cinema.lib.PasswordComplexityConstraint;
import me.aborozdykh.cinema.lib.PasswordsMatch;

/**
 * @author Andrii Borozdykh
 */

@PasswordsMatch(
        password = "password",
        repeatPassword = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserRegistrationDto {
    @NotNull
    @EmailConstraint
    private String email;
    @NotNull
    @PasswordComplexityConstraint
    private String password;
    @NotNull
    @PasswordComplexityConstraint
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
