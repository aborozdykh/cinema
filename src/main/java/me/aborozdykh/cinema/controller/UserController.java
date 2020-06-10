package me.aborozdykh.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import me.aborozdykh.cinema.dto.UserDto;
import me.aborozdykh.cinema.models.User;
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

    @GetMapping("/byemail")
    public UserDto getUser(@RequestParam String email) {
        return new UserDto(userService.findByEmail(email).get());
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
}
