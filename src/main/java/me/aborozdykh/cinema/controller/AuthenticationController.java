package me.aborozdykh.cinema.controller;

import me.aborozdykh.cinema.exceptions.AuthenticationException;
import me.aborozdykh.cinema.models.dto.UserRequestDto;
import me.aborozdykh.cinema.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andrii Borozdykh
 */
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserRequestDto userRequestDto)
            throws AuthenticationException {
        authenticationService.register(userRequestDto.getEmail(), userRequestDto.getPassword());
    }
}
