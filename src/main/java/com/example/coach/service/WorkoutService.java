package com.example.coach.service;

import com.example.coach.model.Exercise;
import com.example.coach.model.User;
import com.example.coach.model.Workout;
import com.example.coach.repository.WorkoutRepository;
import com.example.coach.repository.ExerciseRepository;
import com.example.coach.repository.ExerciseResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    private final ExerciseResultRepository exerciseResultRepository;
    private final WorkoutRepository workoutRepository;
    private final UserService userService;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository, UserService userService, ExerciseRepository exerciseRepository, ExerciseResultRepository exerciseResultRepository) {
        this.userService = userService;
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.exerciseResultRepository = exerciseResultRepository;
    }

    public Workout getWorkoutById(Long workoutId) {
        return workoutRepository.getWorkoutById(workoutId);
    }

    public Long saveWorkoutAndReturnId(Workout workout, User user ) {
        workout.setUser(user);
        return workoutRepository.save(workout).getId();
    }

    public List<Workout> getUsersWorkouts(Long usersId) {
        return workoutRepository.getWorkoutsByUser_Id(usersId);
    }


    public Long createWorkoutForUserAndReturnId(String workoutName, User user) {
        Workout newWorkout = new Workout();
        newWorkout.setName(workoutName);
        return this.saveWorkoutAndReturnId(newWorkout, user);
    }

    private User getCurrentlyloggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userService.getUserByLogin(currentPrincipalName);
    }

    public List<Exercise> getExerciseListByWorkoutId(Long workoutId) {
        Workout workout = workoutRepository.getWorkoutById(workoutId);
        List<Exercise> exercises = exerciseRepository.findAllByWorkouts(workout);
        return exercises;
    }

    public void addExerciseToWorkout(Exercise exercise, Long workoutId) {
        exerciseRepository.save(exercise);
        Long exId = exercise.getId();
        workoutRepository.insertExerciseToWorkout(workoutId, exId);
    }
    public void deleteWorkout(Long workoutId){

        exerciseResultRepository.deleteExerciseResultForWorkout(workoutId);

        workoutRepository.deleteWorkoutExerciseRelationTableRecords(workoutId);
        workoutRepository.deleteById(workoutId);
    }
    public void deleteExerciseFromWorkout(Long exerciseId){
        exerciseResultRepository.deleteExerciseResultForExerciseId(exerciseId);
        workoutRepository.deleteWorkoutExerciseRelationTableRecordsByExId(exerciseId);
        exerciseRepository.delete(exerciseRepository.getOne(exerciseId));
    }

}
