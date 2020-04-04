package com.example.coach.controllers;

import com.example.coach.DTO.WorkoutDto;
import com.example.coach.DTO.WorkoutResultDTO;
import com.example.coach.Exceptions.NoDateInsertedException;
import com.example.coach.Exceptions.NoExcerciseNameInsertedException;
import com.example.coach.Exceptions.NoWokoutNameInsertedException;
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
public class    WorkoutController {

    private final WorkoutService workoutService;
    private final UserService userService;
    private final ExerciseResultService exerciseResultService;
    private final WorkoutCalculatorImpl workoutCalculatorImpl;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public WorkoutController(WorkoutService workoutService, UserService userService, ExerciseResultService exerciseResultService,
                             WorkoutCalculatorImpl workoutCalculatorImpl, ExerciseRepository exerciseRepository) {

        this.workoutService = workoutService;
        this.userService = userService;
        this.exerciseResultService = exerciseResultService;
        this.workoutCalculatorImpl = workoutCalculatorImpl;
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping("/addWorkoutResult")
    public String showAddWorkoutResultPanel(@RequestParam("workoutId") Long workoutId, Model model, @RequestParam("workoutDay") String workoutDay) throws ParseException, NoDateInsertedException{
        if(workoutDay.isEmpty())
            throw new NoDateInsertedException();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse(workoutDay);
        List<Exercise> exerciseList = workoutService.getExerciseListByWorkoutId(workoutId);
        WorkoutResultDTO workoutResultsForm = new WorkoutResultDTO();
        workoutResultsForm.setExercisesResults(exerciseResultService.getAllByDayOfTrainingAndWorkoutId(date, workoutId));

        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        model.addAttribute("workoutResultsForm", workoutResultsForm);
        model.addAttribute("workoutDay", workoutDay);
        model.addAttribute("exerciseList", exerciseList);
        model.addAttribute("workoutId", workoutId);
        model.addAttribute("exerciseResultToAdd", new ExerciseResult());
        return "add-workout-result";
    }

    @PostMapping("/addWorkoutResult")
    public String addWorkoutResult(@RequestParam("workoutId") Long workoutId, Model model, @ModelAttribute("exerciseResultToAdd") ExerciseResult exerciseResultToAdd,
                                   @RequestParam("workoutDay") String workoutDay) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse(workoutDay);

        exerciseResultService.addExerciseResult(exerciseResultToAdd, workoutId, date);

        return "redirect:/add-workout-result?workoutId=" + workoutId + "&workoutDay=" + workoutDay;
    }

    @GetMapping("/createWorkout")
    public String showCreateWorkoutForm(Model model, @RequestParam("workoutName") String workoutName, @RequestParam("createdWorkoutId") Long createdWorkoutId) {
        if(workoutName.isEmpty()) throw new NoWokoutNameInsertedException();

        WorkoutDto exercisesForm = new WorkoutDto();
        Exercise exercise = new Exercise();

        Long newWorkoutId;
        if (createdWorkoutId == null) {
            newWorkoutId = workoutService.createWorkoutForUserAndReturnId(workoutName, Utils.getUser(userService));
            exercisesForm.setExercises(workoutService.getExerciseListByWorkoutId(newWorkoutId));

            model.addAttribute("newWorkoutId", Long.toString(newWorkoutId));
        } else {
            exercisesForm.setExercises(workoutService.getExerciseListByWorkoutId(createdWorkoutId));

            model.addAttribute("newWorkoutId", createdWorkoutId);
        }

        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        model.addAttribute("workoutName", workoutName);
        model.addAttribute("exercisesForm", exercisesForm);
        model.addAttribute("exerciseToAdd", exercise);
        return "create-workout";
    }
    @GetMapping("/createWorkoutForPupil")
    public String showCreateWorkoutFormForCoach(Model model, @RequestParam("workoutName") String workoutName, @RequestParam("createdWorkoutId") Long createdWorkoutId,
                                                @RequestParam Long pupilId ) {
        if(workoutName.isEmpty()) throw new NoWokoutNameInsertedException();

        WorkoutDto exercisesForm = new WorkoutDto();
        Exercise exercise = new Exercise();

        Long newWorkoutId;
        if (createdWorkoutId == null) {
            newWorkoutId = workoutService.createWorkoutForUserAndReturnId(workoutName, userService.getUserById(pupilId));
            exercisesForm.setExercises(workoutService.getExerciseListByWorkoutId(newWorkoutId));

            model.addAttribute("newWorkoutId", Long.toString(newWorkoutId));
        } else {
            exercisesForm.setExercises(workoutService.getExerciseListByWorkoutId(createdWorkoutId));

            model.addAttribute("newWorkoutId", createdWorkoutId);
        }

        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        model.addAttribute("workoutName", workoutName);
        model.addAttribute("exercisesForm", exercisesForm);
        model.addAttribute("exerciseToAdd", exercise);
        return "create-workout";
    }

    @PostMapping("/createWorkout/addExercise")
    public String addExerciseToWorkout(@ModelAttribute("exerciseToAdd") Exercise exerciseToAdd, @RequestParam("newWorkoutId") Long newWorkoutId, @RequestParam("workoutName") String workoutName, Model model) {
        if(exerciseToAdd.getName().isEmpty()) throw new NoExcerciseNameInsertedException();
        workoutService.addExerciseToWorkout(exerciseToAdd, newWorkoutId);

        return "redirect:/create-workout?workoutName=" + workoutName + "&createdWorkoutId=" + newWorkoutId;
    }

    @GetMapping("/showWorkoutResults")
    public String showWorkoutReults(Model model, @RequestParam String workoutId) {

        Workout workoutToShow = workoutService.getWorkoutById(Long.parseLong(workoutId));
        model.addAttribute("workoutToShow", workoutToShow);
        Map<Date, Double> workoutResultsVMap = workoutCalculatorImpl.getWorkoutVMap(workoutToShow.getUser().getId(), Long.parseLong(workoutId));
        model.addAttribute("surveyMap", workoutResultsVMap);
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));

        return "show-workout-results";
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

        return "show-exercise-results";
    }
    @GetMapping("/deleteWorkout")
    public String deleteWorkout(Model model, @RequestParam Long workoutId){
        workoutService.deleteWorkout(workoutId);
        return "redirect:/main";
    }
    @GetMapping("/modifyWorkout")
    public String modifyWorkout(Model model, @RequestParam Long workoutId,  @RequestParam("workoutName") String workoutName){

        WorkoutDto exercisesForm = new WorkoutDto();

        Exercise exercise = new Exercise();
        exercisesForm.setExercises(workoutService.getExerciseListByWorkoutId(workoutId));
        model.addAttribute("workoutId", workoutId);
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        model.addAttribute("exercisesForm", exercisesForm);
        model.addAttribute("exerciseToAdd", exercise);
        model.addAttribute("workoutName", workoutName);
        return "modify-workout";
    }
    @PostMapping("/modifyWorkout/addExercise")
    public String addExerciseToModifiedWorkout(@ModelAttribute("exerciseToAdd") Exercise exerciseToAdd, @RequestParam("workoutId") Long workoutId, @RequestParam("workoutName") String workoutName, Model model) {
        if(exerciseToAdd.getName().isEmpty()) throw new NoExcerciseNameInsertedException();

        workoutService.addExerciseToWorkout(exerciseToAdd, workoutId);

        return "redirect:/modify-workout?workoutName=" + workoutName + "&workoutId=" + workoutId;
    }
    @PostMapping("/modifyWorkout/removeExercise")
    public String removeExercise(@ModelAttribute("exerciseToRemoveId") Long exerciseToRemoveId, @RequestParam("workoutId") Long workoutId, @RequestParam("workoutName") String workoutName, Model model) {
        workoutService.deleteExerciseFromWorkout(exerciseToRemoveId);

        return "redirect:/modify-workout?workoutName=" + workoutName + "&workoutId=" + workoutId;
    }
}
