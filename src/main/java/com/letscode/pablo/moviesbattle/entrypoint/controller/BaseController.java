package com.letscode.pablo.moviesbattle.entrypoint.controller;

import com.letscode.pablo.moviesbattle.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

    protected int getLoggedUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return ((User)authentication.getPrincipal()).getId();
        }
        return 0;
    }
}
