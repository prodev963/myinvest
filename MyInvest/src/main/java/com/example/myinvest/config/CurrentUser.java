package com.example.myinvest.config;

import com.example.myinvest.entity.UserEntity;
import com.example.myinvest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CurrentUser {
    private final UserService userService;

    @Autowired
    public CurrentUser(UserService userService) {
        this.userService = userService;
    }

    public UserEntity getCurrentUser(){
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if (authentication != null) {
            String username = (String) authentication.getPrincipal();
            UserDetails userDetails = userService.loadUserByUsername(username);
            return (UserEntity) userDetails;
        }
        return null;
    }
}
