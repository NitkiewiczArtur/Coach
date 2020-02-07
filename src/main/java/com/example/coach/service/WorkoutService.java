package com.example.coach.service;

import com.example.coach.DTO.WorkoutDto;
import com.example.coach.model.Exercise;
import com.example.coach.model.User;
import com.example.coach.model.Workout;
import com.example.coach.repository.ExerciseRepository;
import com.example.coach.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkoutService {

    private WorkoutRepository workoutRepository;
    private UserService userService;
    private ExerciseRepository exerciseRepository;
    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository, UserService userService, ExerciseRepository exerciseRepository){
        this.userService = userService;
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public Workout getWorkoutById(Long workoutId){
        return workoutRepository.getWorkoutById(workoutId);
    }
    public Long saveWorkoutAndReturnId(Workout workout){
        workout.setUser(getCurrentlyloggedUser());
        return workoutRepository.save(workout).getId();
    }
    public List<Workout> getUsersWorkouts(Long usersId){
        return workoutRepository.getWorkoutsByUser_Id(usersId);
    }
    public void createWorkoutAndReturnId(WorkoutDto form){
        Workout newWorkout = new Workout();

        newWorkout.setName("nowyW");

        newWorkout.setExercises(form.getExercises());
        this.saveWorkoutAndReturnId(newWorkout);

    }
    public Long createWorkoutAndReturnId(String workoutName){
        Workout newWorkout = new Workout();
        newWorkout.setName(workoutName);
        return this.saveWorkoutAndReturnId(newWorkout);
    }

    private User getCurrentlyloggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userService.getUserByLogin(currentPrincipalName);
    }
    public List<Exercise> getExerciseListByWorkoutId(Long workoutId){
       return  exerciseRepository.findAllByWorkouts(workoutRepository.getWorkoutById(workoutId));
    }
    public void addExercise(Exercise exercise, Long workoutId){
        Workout workoutToInsertExerciseInto = workoutRepository.getWorkoutById(workoutId);
        List<Workout> workouts = new ArrayList<>();
        workouts.add(workoutToInsertExerciseInto);
        exercise.setWorkouts(workouts);
        exerciseRepository.save(exercise);

    }


}
