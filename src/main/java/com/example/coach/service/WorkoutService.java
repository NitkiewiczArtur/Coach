package com.example.coach.service;

import com.example.coach.DTO.WorkoutCreationDto;
import com.example.coach.model.User;
import com.example.coach.model.Workout;
import com.example.coach.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    private WorkoutRepository workoutRepository;
    private UserService userService;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository, UserService userService){
        this.userService = userService;
        this.workoutRepository = workoutRepository;
    }

    public Workout getWorkoutById(Long workoutId){
        return workoutRepository.getWorkoutById(workoutId);
    }
    public void saveWorkout(Workout workout){
        workout.setUser(getCurrentlyloggedUser());
        workoutRepository.save(workout);
    }
    public List<Workout> getUsersWorkouts(Long usersId){
        return workoutRepository.getWorkoutsByUser_Id(usersId);
    }
    public void createWorkout(WorkoutCreationDto form){
        Workout newWorkout = new Workout();

        newWorkout.setName("nowyW");

        newWorkout.setExercises(form.getExercises());
        this.saveWorkout(newWorkout);

    }

    private User getCurrentlyloggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userService.getUserByLogin(currentPrincipalName);
    }

}
