package me.aborozdykh.cinema.controller;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import me.aborozdykh.cinema.exceptions.AuthenticationException;
import me.aborozdykh.cinema.models.dto.UserRegistrationDto;
import me.aborozdykh.cinema.security.AuthenticationService;
import me.aborozdykh.cinema.service.RoleService;
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
    private final RoleService roleService;

    @PostConstruct
    public void insertRolesToDb(){

    }

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, RoleService roleService) {
        this.authenticationService = authenticationService;
        this.roleService = roleService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody @Valid UserRegistrationDto userRegistrationDto)
            throws AuthenticationException {
        authenticationService.register(userRegistrationDto.getEmail(),
                userRegistrationDto.getPassword());
    }
}
