package com.letscode.pablo.moviesbattle.entrypoint.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseController {
    protected String getLoggedUsername() {
        String username = null;
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            username = ((UserDetails)authentication.getPrincipal()).getUsername();
        }
        return username;
    }
}
