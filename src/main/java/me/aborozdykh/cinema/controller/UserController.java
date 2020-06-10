package me.aborozdykh.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import me.aborozdykh.cinema.models.User;
import me.aborozdykh.cinema.models.dto.UserRequestDto;
import me.aborozdykh.cinema.models.dto.UserResponseDto;
import me.aborozdykh.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public String inject() {
        userService.add(new User("nif-nif@pigs.com", "nif-nif"));
        userService.add(new User("naf-naf@pigs.com", "naf-naf"));
        userService.add(new User("nuf-nuf@pigs.com", "nuf-nuf"));
        return "Data was injected";
    }

    @PostMapping("/add")
    public void addUser(@RequestBody UserRequestDto userRequestDto) {
        var user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        userService.add(user);
    }

    @GetMapping("/byemail")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        return new UserResponseDto(userService.findByEmail(email));
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }
}
