package com.example.coach.controllers;

import com.example.coach.DTO.WorkoutCreationDto;
import com.example.coach.DTO.WorkoutResultDTO;
import com.example.coach.model.Exercise;
import com.example.coach.model.ExerciseResult;
import com.example.coach.model.Workout;
import com.example.coach.service.ExerciseResultService;
import com.example.coach.service.UserService;
import com.example.coach.service.WorkoutService;
import com.example.coach.utils.WorkoutCalculatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
    public String showAddWorkoutResultPanel (@RequestParam("workoutId") Long workoutId, Model model, @ModelAttribute WorkoutResultDTO workoutResultsForm, @RequestParam("workoutDay") String workoutDay) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");


        Date date = formatter.parse(workoutDay);
        workoutResultsForm = new WorkoutResultDTO();
        workoutResultsForm.setExercisesResults(exerciseResultService.getAllByDayOfTraining(date));
        if(workoutResultsForm.getExercisesResults().size() == 0)
        workoutResultsForm.addExerciseResult(new ExerciseResult(), null);

        model.addAttribute("workoutResultsForm", workoutResultsForm);
        model.addAttribute("workoutDay",workoutDay);
      //  ExerciseResult exerciseResultToInsert = new ExerciseResult();
       // model.addAttribute("exerciseResultToInsert", exerciseResultToInsert);
        model.addAttribute("workoutId", workoutId);

        return "addWorkoutResult";
    }
    @PostMapping("/addWorkoutResult")
    public String addWorkoutResult(@RequestParam("workoutId") Long workoutId, Model model, @ModelAttribute("exerciseResultToInsert") ExerciseResult exerciseResultToInsert,
                                   @ModelAttribute WorkoutResultDTO workoutResultsForm, @RequestParam("workoutDay") Date workoutDay){

        exerciseResultService.addWorkoutResult(workoutResultsForm, workoutId, workoutDay);
     //   exerciseResultService.addExerciseResult(exerciseResultToInsert, workoutId);
        workoutResultsForm.setExercisesResults(exerciseResultService.getAllByDayOfTraining(workoutDay));
        model.addAttribute("workoutResultsForm", workoutResultsForm);

        return "redirect:/addWorkoutResult?workoutId="+workoutId;
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
    @GetMapping("/showWorkoutResults")
    public String showWorkoutReults(Model model, @RequestParam String workoutId){
        Workout workoutToShow = workoutService.getWorkoutById(Long.parseLong(workoutId));
        model.addAttribute("workoutToShow", workoutToShow);

        Map<Date, Double> workoutResultsVMap = workoutCalculatorImpl.getWorkoutVMap(workoutToShow.getUser().getId());
        model.addAttribute("workoutResultsVMap", workoutResultsVMap);


        return "showWorkoutResults";
    }
}
