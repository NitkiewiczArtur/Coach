package com.example.coach.controllers;

import com.example.coach.model.Coach;
import com.example.coach.model.User;
import com.example.coach.model.Workout;
import com.example.coach.repository.CoachRepository;
import com.example.coach.service.ExerciseResultService;
import com.example.coach.service.UserService;
import com.example.coach.service.WorkoutService;
import com.example.coach.utils.Utils;
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
    CoachRepository coachRepository;
    @Autowired
    UserService userService;
    @Autowired
    WorkoutService workoutService;
    @Autowired
    ExerciseResultService exerciseResultService;

    @GetMapping("/home")
    public String getHomeView(Model model) {

        User currentlyLoggedUser = Utils.getUser(userService);
        List<Workout> workoutList = workoutService.getUsersWorkouts(currentlyLoggedUser.getId());

        model.addAttribute("currentlyLoggedUser", currentlyLoggedUser);
        model.addAttribute("workoutList", workoutList);
        return "home";
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
            return new ModelAndView("main_coach", "currentlyLoggedUser", currentlyLoggedUser);
        }

    }
    private boolean isCoach(){
        User currentlyLoggedUser = Utils.getUser(userService);
        if(coachRepository.findByLogin(currentlyLoggedUser.getLogin()) !=null)
        return true;
       else return false;

    }

}
