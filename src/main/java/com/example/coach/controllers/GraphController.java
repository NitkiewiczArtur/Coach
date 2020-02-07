package com.example.coach.controllers;

import com.example.coach.model.Workout;
import com.example.coach.service.ExerciseResultService;
import com.example.coach.service.UserService;
import com.example.coach.service.WorkoutService;
import com.example.coach.utils.Utils;
import com.example.coach.utils.WorkoutCalculatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Controller
public class GraphController {
	@Autowired
	WorkoutService workoutService;
	@Autowired
	UserService userService;
	@Autowired
	ExerciseResultService exerciseResultService;
	@Autowired
	WorkoutCalculatorImpl workoutCalculatorImpl;

	@GetMapping("/displayBarGraph")
	public String barGraph(Model model,  @RequestParam String workoutId) {
		Workout workoutToShow = workoutService.getWorkoutById(Long.parseLong(workoutId));
		model.addAttribute("workoutToShow", workoutToShow);

		Map<Date, Double> workoutResultsVMap = workoutCalculatorImpl.getWorkoutVMap(workoutToShow.getUser().getId(), Long.parseLong(workoutId));
		model.addAttribute("workoutResultsVMap", workoutResultsVMap);
		model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));

		model.addAttribute("surveyMap", workoutResultsVMap);
		return "barGraph";
	}

	@GetMapping("/displayPieChart")
	public String pieChart(Model model) {
		model.addAttribute("pass", 50);
		model.addAttribute("fail", 50);
		return "pieChart";
	}

	public String showWorkoutReults(Model model, @RequestParam String workoutId){

		Workout workoutToShow = workoutService.getWorkoutById(Long.parseLong(workoutId));
		model.addAttribute("workoutToShow", workoutToShow);

		Map<Date, Double> workoutResultsVMap = workoutCalculatorImpl.getWorkoutVMap(workoutToShow.getUser().getId(), Long.parseLong(workoutId));
		model.addAttribute("workoutResultsVMap", workoutResultsVMap);
		model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));

		return "showWorkoutResults";
	}

}
