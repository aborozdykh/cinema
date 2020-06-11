package me.aborozdykh.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import me.aborozdykh.cinema.models.dto.UserResponseDto;
import me.aborozdykh.cinema.models.mappers.UserMapper;
import me.aborozdykh.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andrii Borozdykh
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/byemail")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        return userMapper.getUserResponseDtoFromUser(userService.findByEmail(email));
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAll().stream()
                .map(userMapper::getUserResponseDtoFromUser)
                .collect(Collectors.toList());
    }
}
