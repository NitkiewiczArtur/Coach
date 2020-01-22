package com.example.coach.service;

import com.example.coach.model.Workout;
import com.example.coach.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    private WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository){
        this.workoutRepository = workoutRepository;
    }

    public void saveWorkout(Workout workout){
        workoutRepository.save(workout);
    }
    public List<Workout> getUsersWorkouts(Long usersId){
        return workoutRepository.getWorkoutsByUsers_Id(usersId);
    }


}
