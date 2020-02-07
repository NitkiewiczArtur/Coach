package com.example.coach.controllers;

import com.example.coach.model.User;
import com.example.coach.model.Workout;
import com.example.coach.service.ExerciseResultService;
import com.example.coach.service.UserService;
import com.example.coach.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {



    @Autowired
    UserService userService;
    @Autowired
    WorkoutService workoutService;
    @Autowired
    ExerciseResultService exerciseResultService;
    @GetMapping("/home")
    public String getHomeView(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentlyLoggedUser = userService.getUserByLogin(currentPrincipalName);
        List<Workout> workoutList = workoutService.getUsersWorkouts(currentlyLoggedUser.getId());

        model.addAttribute("currentlyLoggedUser", currentlyLoggedUser);
        model.addAttribute("workoutList", workoutList);
        return "home";
    }


    @GetMapping("/main")
    public ModelAndView getMainView(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentlyLoggedUser = userService.getUserByLogin(currentPrincipalName);
        List<Workout> workoutList = workoutService.getUsersWorkouts(currentlyLoggedUser.getId());

        model.addAttribute("workoutList", workoutList);
        return new ModelAndView("main", "currentlyLoggedUser", currentlyLoggedUser);
    }

}
