package com.example.coach.controllers;

import com.example.coach.model.User;
import com.example.coach.model.Workout;
import com.example.coach.service.CoachService;
import com.example.coach.service.UserService;
import com.example.coach.service.WorkoutService;
import com.example.coach.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {


    private final CoachService coachService;
    private final UserService userService;
    private final WorkoutService workoutService;


    @Autowired
    public MainController(CoachService coachService, UserService userService, WorkoutService workoutService) {
        this.coachService = coachService;
        this.userService = userService;
        this.workoutService = workoutService;
    }

    @GetMapping("/main")
    public ModelAndView getMainView(Model model) {
        User currentlyLoggedUser = Utils.getUser(userService);

        if(isCoach()== false){
            List<Workout> workoutList = workoutService.getUsersWorkouts(currentlyLoggedUser.getId());
            model.addAttribute("workoutList", workoutList);
            return new ModelAndView("main", "currentlyLoggedUser", currentlyLoggedUser);
        }else{
            List<User> pupilsList = userService.getAllByCoachId(3L);
            model.addAttribute("pupilsList", pupilsList);
            return new ModelAndView("main-coach", "currentlyLoggedUser", currentlyLoggedUser);
        }
    }


    @GetMapping("/mainCoachViewOnPupil")
    public String getMainCoachView(Model model, @RequestParam Long pupilId) {

        List<Workout> workoutList = workoutService.getUsersWorkouts(pupilId);

        model.addAttribute("pupilId", pupilId);
        model.addAttribute("workoutList", workoutList);
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));

        return "main-coach-view-on-pupil";
    }
    private boolean isCoach(){
        User currentlyLoggedUser = Utils.getUser(userService);
        if(coachService.getCoachByLogin(currentlyLoggedUser.getLogin()) !=null)
        return true;
       else return false;

    }

}
