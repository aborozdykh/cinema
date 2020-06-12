package me.aborozdykh.cinema.controller;

import me.aborozdykh.cinema.exceptions.AuthenticationException;
import me.aborozdykh.cinema.models.dto.UserRegistrationDto;
import me.aborozdykh.cinema.models.dto.UserRequestDto;
import me.aborozdykh.cinema.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @PostMapping("/user-dto")
    public UserRequestDto getUser(){
        var userRequestDto = new UserRequestDto();
        var securityContext = SecurityContextHolder.getContext();
        var authentication = securityContext.getAuthentication();
        var principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            userRequestDto.setEmail(userDetails.getUsername());
            userRequestDto.setPassword(userDetails.getPassword());
        }


    }
}
