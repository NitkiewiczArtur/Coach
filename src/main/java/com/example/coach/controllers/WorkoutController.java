package com.example.coach.controllers;

import com.example.coach.DTO.WorkoutCreationDto;
import com.example.coach.model.Exercise;
import com.example.coach.model.ExerciseResult;
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
        Workout workoutToShow = workoutService.getWorkoutById(Long.parseLong(workoutId));
        model.addAttribute("workoutToShow", workoutToShow);

        List<ExerciseResult> exerciseResultList = exerciseResultService.getAllExerciseResultsByWorkoutId(Long.parseLong(workoutId));
        model.addAttribute("exerciseResultList", exerciseResultList);

        return "showWorkoutResults";
    }
    @GetMapping("/addWorkout")
    public String showCreateForm(Model model){
        WorkoutCreationDto exercisesForm = new WorkoutCreationDto();
        for (int i = 1 ; i <=10; i++){
            exercisesForm.addExercise(new Exercise());
        }
        model.addAttribute("form", exercisesForm);
        return "addWorkout";
    }
    @PostMapping("/addWorkout")
    public String saveWorkout(Model model,@ModelAttribute WorkoutCreationDto form){


        Workout newWorkout = new Workout();

        newWorkout.setName("nowyW");

        newWorkout.setExercises(form.getExercises());
        workoutService.saveWorkout(newWorkout);
        return "redirect:/main";
    }
}
