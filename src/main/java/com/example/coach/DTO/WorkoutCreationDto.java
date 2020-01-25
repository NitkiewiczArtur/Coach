package com.example.coach.DTO;

import com.example.coach.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class WorkoutCreationDto {
    private List<Exercise> exercises = new ArrayList<>();

    public void addExercise(Exercise exercise){
        this.exercises.add(exercise);
    }

    public WorkoutCreationDto(List<Exercise> exercises) {
        this.exercises = exercises;
    }
    public WorkoutCreationDto(){

    }
    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
