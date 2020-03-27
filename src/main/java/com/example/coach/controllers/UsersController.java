package com.example.coach.controllers;

import com.example.coach.model.User;
import com.example.coach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ModelAndView getUsersView() {
        List<User> users = userService.getAllUsers();
        return new ModelAndView("users", "allUsers", users);
    }

    @GetMapping(value = "/adduser")
    public String getAddUserView() {

        return "adduser";
    }

    @PostMapping(value = "/adduser")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "index";
    }

    @PostMapping(value = "/userDetails")
    public void userDetails(@ModelAttribute User user) {
        System.out.println(user.getName() + " " + user.getSurname());
    }

}
