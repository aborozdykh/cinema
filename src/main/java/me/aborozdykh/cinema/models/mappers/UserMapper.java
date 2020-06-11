package me.aborozdykh.cinema.models.mappers;

import me.aborozdykh.cinema.models.User;
import me.aborozdykh.cinema.models.dto.UserRequestDto;
import me.aborozdykh.cinema.models.dto.UserResponseDto;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Borozdykh
 */
@Component
public class UserMapper {
    public User getUserFromUserRequestDto(UserRequestDto userRequestDto) {
        var user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }

    public UserResponseDto getUserResponseDtoFromUser(User user) {
        if (user == null) {
            return null;
        }
        var userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        return userResponseDto;
    }
}
