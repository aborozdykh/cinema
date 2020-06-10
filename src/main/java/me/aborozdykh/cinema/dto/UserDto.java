package me.aborozdykh.cinema.dto;

import me.aborozdykh.cinema.models.User;

/**
 * @author Andrii Borozdykh
 */
public class UserDto {
    private String email;
    private String password;

    public UserDto(User user) {
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
    }

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
}
