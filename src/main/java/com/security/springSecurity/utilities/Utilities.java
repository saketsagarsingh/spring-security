package com.security.springSecurity.utilities;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Utilities {

    private static final String JWT_TOKEN_KEYWORD="JWT";

    public String getUserFromContext(){
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();
    }
}
