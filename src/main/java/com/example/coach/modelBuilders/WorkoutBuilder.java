package com.example.coach.modelBuilders;

import com.example.coach.model.Exercise;
import com.example.coach.model.ExerciseResult;
import com.example.coach.model.User;
import com.example.coach.model.Workout;

import java.util.List;

public class WorkoutBuilder {
    private Long id;
    private String name;
    private List<Exercise> exercises;
    private List<ExerciseResult> exerciseResults;
    private User user;

    public WorkoutBuilder id (Long id){
        this.id = id;
        return this;
    }
    public WorkoutBuilder name (String name){
        this.name = name;
        return this;
    }
    public WorkoutBuilder exercises(List<Exercise> exercises){
        this.exercises = exercises;
        return this;
    }
    public WorkoutBuilder exerciseResults (List<ExerciseResult> exerciseResults){
        this.exerciseResults = exerciseResults;
        return this;
    }
    public WorkoutBuilder user (User user) {
        this.user = user;
        return this;
    }
    public Workout build(){
        if(name.isEmpty())
            throw new IllegalStateException("name cannot be empty");
        if(exercises.isEmpty())
            throw new IllegalStateException("exercise list cannot be empty");

        Workout workout = new Workout();
        workout.setId(this.id);
        workout.setName(this.name);
        workout.setExercises(this.exercises);
        workout.setExerciseResults(this.exerciseResults);
        workout.setUser(this.user);
        return workout;
    }
}
