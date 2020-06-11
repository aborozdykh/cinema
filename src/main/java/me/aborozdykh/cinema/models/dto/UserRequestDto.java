package me.aborozdykh.cinema.models.dto;

/**
 * @author Andrii Borozdykh
 */
public class UserRequestDto {
    private String email;
    private String password;

    public UserRequestDto() {
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
