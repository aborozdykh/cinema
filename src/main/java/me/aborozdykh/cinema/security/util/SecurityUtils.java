package me.aborozdykh.cinema.security.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Borozdykh
 */
@Component
public class SecurityUtils {
    public String getEmail() {
        var email = "";
        var securityContext = SecurityContextHolder.getContext();
        var authentication = securityContext.getAuthentication();
        var principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            email = userDetails.getUsername();
        }
        return email;
    }
}
