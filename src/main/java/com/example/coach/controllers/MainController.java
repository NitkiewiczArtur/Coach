package com.example.coach.controllers;

import com.example.coach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping("/main")
    public ModelAndView getMainView(){

    }
}
