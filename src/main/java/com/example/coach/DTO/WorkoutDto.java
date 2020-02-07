package com.example.coach.DTO;

import com.example.coach.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDto {
    private Long id;
    private List<Exercise> exercises = new ArrayList<>();

    public void addExercise(Exercise exercise){
        this.exercises.add(exercise);
    }

    public WorkoutDto(List<Exercise> exercises) {
        this.exercises = exercises;
    }
    public WorkoutDto(){ }
    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
