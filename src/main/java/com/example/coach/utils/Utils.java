package com.example.coach.utils;

import com.example.coach.model.User;
import com.example.coach.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class Utils {

    public static User getUser(UserService userService) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentlyLoggedUser = userService.getUserByLogin(currentPrincipalName);
        return currentlyLoggedUser;
    }
    public static User createTestUser(){
        User user = new User();
        user.setId(1L);
        user.setLogin("tester");
        user.setName("name");
        user.setSurname("surname");
        return user;
    }
}
