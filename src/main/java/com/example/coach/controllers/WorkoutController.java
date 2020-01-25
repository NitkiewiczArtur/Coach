package com.example.coach.controllers;

import com.example.coach.DTO.WorkoutCreationDto;
import com.example.coach.model.Exercise;
import com.example.coach.model.ExerciseResult;
import com.example.coach.model.Workout;
import com.example.coach.service.ExerciseResultService;
import com.example.coach.service.UserService;
import com.example.coach.service.WorkoutService;
import com.example.coach.utils.WorkoutCalculator;
import com.example.coach.utils.WorkoutCalculatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class WorkoutController {
    @Autowired
    WorkoutService workoutService;
    @Autowired
    UserService userService;
    @Autowired
    ExerciseResultService exerciseResultService;
    @Autowired
    WorkoutCalculatorImpl workoutCalculatorImpl;



    @GetMapping("/addWorkoutResult")
    public String getMainView(Model model, @RequestParam String workoutId){
        ExerciseResult exerciseResultToInsert = new ExerciseResult();
        exerciseResultToInsert.setWorkout(workoutService.getWorkoutById(Long.parseLong(workoutId)));
        model.addAttribute("exerciseResultToInsert", exerciseResultToInsert);

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

        Map<Date, Double> workoutResultsVMap = workoutCalculatorImpl.getWorkoutVMap(workoutToShow.getUser().getId());
        model.addAttribute("workoutResultsVMap", workoutResultsVMap);

        List<Date> exerciseResultList = exerciseResultService.getExResDatesForUserId(workoutToShow.getUser().getId());
                /*.getAllExerciseResultsByWorkoutId(Long.parseLong(workoutId));*/
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
    public String createWorkout(Model model, @ModelAttribute WorkoutCreationDto form){
        workoutService.createWorkout(form);
        return "redirect:/main";
    }
}
