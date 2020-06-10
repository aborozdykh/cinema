package me.aborozdykh.cinema.models.dto;

import me.aborozdykh.cinema.models.User;

/**
 * @author Andrii Borozdykh
 */
public class UserResponseDto {
    private Long userId;
    private String email;
    private String password;

    public UserResponseDto() {
    }

    public UserResponseDto(User user) {
        this.setUserId(user.getId());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
