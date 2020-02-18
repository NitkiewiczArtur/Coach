package com.example.coach.controllers;

import com.example.coach.DTO.WorkoutDto;
import com.example.coach.DTO.WorkoutResultDTO;
import com.example.coach.model.Exercise;
import com.example.coach.model.ExerciseResult;
import com.example.coach.model.Workout;
import com.example.coach.repository.ExerciseRepository;
import com.example.coach.service.ExerciseResultService;
import com.example.coach.service.UserService;
import com.example.coach.service.WorkoutService;
import com.example.coach.utils.Utils;
import com.example.coach.utils.WorkoutCalculatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Autowired
    ExerciseRepository exerciseRepository;


    @GetMapping("/addWorkoutResult")
    public String showAddWorkoutResultPanel(@RequestParam("workoutId") Long workoutId, Model model, @RequestParam("workoutDay") String workoutDay) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse(workoutDay);
        List<Exercise> exerciseList = workoutService.getExerciseListByWorkoutId(workoutId);
        WorkoutResultDTO workoutResultsForm = new WorkoutResultDTO();
        /*if(workoutResultsForm.getExercisesResults().size() == 0)
            workoutResultsForm.addExerciseResult(new ExerciseResult(), null);*/
        workoutResultsForm.setExercisesResults(exerciseResultService.getAllByDayOfTrainingAndWorkoutId(date, workoutId));

        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        model.addAttribute("workoutResultsForm", workoutResultsForm);
        model.addAttribute("workoutDay", workoutDay);
        model.addAttribute("exerciseList", exerciseList);
        model.addAttribute("workoutId", workoutId);
        model.addAttribute("exerciseResultToAdd", new ExerciseResult());
        return "addWorkoutResult";
    }

    @PostMapping("/addWorkoutResult")
    public String addWorkoutResult(@RequestParam("workoutId") Long workoutId, Model model, @ModelAttribute("exerciseResultToAdd") ExerciseResult exerciseResultToAdd,
                                   @RequestParam("workoutDay") String workoutDay) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse(workoutDay);

        exerciseResultService.addExerciseResult(exerciseResultToAdd, workoutId, date);

        return "redirect:/addWorkoutResult?workoutId=" + workoutId + "&workoutDay=" + workoutDay;
    }

    @GetMapping("/addWorkout")
    public String showCreateForm(Model model, @RequestParam("workoutName") String workoutName, @RequestParam("createdWorkoutId") Long createdWorkoutId) {


        WorkoutDto exercisesForm = new WorkoutDto();

        Exercise exercise = new Exercise();

        Long newWorkoutId;
        if (createdWorkoutId == null) {
            newWorkoutId = workoutService.createWorkoutAndReturnId(workoutName);
            exercisesForm.setExercises(workoutService.getExerciseListByWorkoutId(newWorkoutId));
            model.addAttribute("newWorkoutIdEEE", Long.toString(newWorkoutId));
        } else {
            exercisesForm.setExercises(workoutService.getExerciseListByWorkoutId(createdWorkoutId));
            model.addAttribute("newWorkoutIdEEE", createdWorkoutId);
        }

        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        model.addAttribute("workoutName", workoutName);
        model.addAttribute("exercisesForm", exercisesForm);
        model.addAttribute("exerciseToAdd", exercise);
        return "addWorkout";
    }

    @PostMapping("/addWorkout/addExercise")
    public String addExerciseToWorkout(@ModelAttribute("exerciseToAdd") Exercise exerciseToAdd, @RequestParam("newWorkoutIdEEE") Long newWorkoutIdEEE, @RequestParam("workoutName") String workoutName, Model model) {
        workoutService.addExerciseToWorkout(exerciseToAdd, newWorkoutIdEEE);

        return "redirect:/addWorkout?workoutName=" + workoutName + "&createdWorkoutId=" + newWorkoutIdEEE;
    }

    @GetMapping("/showWorkoutResults")
    public String showWorkoutReults(Model model, @RequestParam String workoutId) {

        Workout workoutToShow = workoutService.getWorkoutById(Long.parseLong(workoutId));
        model.addAttribute("workoutToShow", workoutToShow);
        Map<Date, Double> workoutResultsVMap = workoutCalculatorImpl.getWorkoutVMap(workoutToShow.getUser().getId(), Long.parseLong(workoutId));
        model.addAttribute("surveyMap", workoutResultsVMap);
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));

        return "showWorkoutResults";
    }

    @GetMapping("/showExerciseResults")
    public String showExerciseResults(Model model, @RequestParam Long workoutId, @RequestParam Long exerciseId) {
        Exercise exerciseToShow = exerciseRepository.getOne(exerciseId);
        Workout workoutToShow = workoutService.getWorkoutById(workoutId);
        model.addAttribute("workoutToShow", workoutToShow);

        Map<Date, Double> exerciseResultsVMap = workoutCalculatorImpl.getExerciseVMap(workoutToShow.getUser().getId(), workoutId, exerciseId);
        model.addAttribute("surveyMap", exerciseResultsVMap);
        model.addAttribute("exerciseToShow", exerciseToShow);
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));

        return "showExerciseResults";
    }
}
