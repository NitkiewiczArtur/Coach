package com.example.coach.controllers;

import com.example.coach.model.ExerciseResult;
import com.example.coach.model.User;
import com.example.coach.service.ExerciseResultService;
import com.example.coach.service.UserService;
import com.example.coach.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.List;

@Controller
public class WorkoutController {
    @Autowired
    WorkoutService workoutService;
    @Autowired
    UserService userService;
    @Autowired
    ExerciseResultService exerciseResultService;

    @GetMapping("/addWorkoutResult")
    public String getMainView(Model model){
        model.addAttribute("exerciseResultToInsert", new ExerciseResult());
        return "addWorkoutResult";
    }
    @PostMapping("/addWorkoutResult")
    public String addWorkoutResult(@ModelAttribute("exerciseResultToInsert") ExerciseResult exerciseResultToInsert){
        exerciseResultService.addExerciseResult(exerciseResultToInsert);
        return "redirect:/main";
    }
    @GetMapping("/showWorkoutResults")
    public String showWorkoutReults(Model model, @RequestParam String workoutId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentlyLoggedUser = userService.getUserByLogin(currentPrincipalName);
        List<ExerciseResult> exerciseResultList = exerciseResultService.getAllExerciseResultsByWorkoutId(Long.parseLong(workoutId));
        model.addAttribute("exerciseResultList", exerciseResultList);
        return "showWorkoutResults";
    }

}
