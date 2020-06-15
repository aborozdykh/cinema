package me.aborozdykh.cinema.models.dto;

import me.aborozdykh.cinema.annotations.EmailConstraint;
import me.aborozdykh.cinema.annotations.PasswordComplexityConstraint;
import me.aborozdykh.cinema.annotations.PasswordsMatch;

/**
 * @author Andrii Borozdykh
 */

@PasswordsMatch(
        password = "password",
        repeatPassword = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserRegistrationDto {
    @EmailConstraint
    private String email;
    @PasswordComplexityConstraint
    private String password;
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
