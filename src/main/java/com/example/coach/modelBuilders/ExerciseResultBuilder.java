package com.example.coach.modelBuilders;

import com.example.coach.model.Exercise;
import com.example.coach.model.ExerciseResult;
import com.example.coach.model.User;
import com.example.coach.model.Workout;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ExerciseResultBuilder {
    private Long id;
    private Workout workout;
    private Exercise exercise;
    private Double load;
    private int reps;
    private Integer timeInSeconds;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dayOfTraining;

    public ExerciseResultBuilder id (Long id){
        this.id = id;
        return this;
    }
    public ExerciseResultBuilder workout (Workout workout){
        this.workout = workout;
        return this;
    }
    public ExerciseResultBuilder exercise(Exercise exercises){
        this.exercise = exercise;
        return this;
    }
    public ExerciseResultBuilder load (Double load){
        this.load = load;
        return this;
    }
    public ExerciseResultBuilder reps (int reps) {
        this.reps = reps;
        return this;
    }

    public ExerciseResultBuilder timeInSeconds (Integer timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
        return this;
    }

    public ExerciseResultBuilder dayOfTraining (Date dayOfTraining) {
        this.dayOfTraining = dayOfTraining;
        return this;
    }
    public ExerciseResult build(){
        if(workout == null)
            throw new IllegalStateException("name cannot be empty");
        if(exercise == null)
            throw new IllegalStateException("exercise list cannot be empty");

        ExerciseResult exerciseResult = new ExerciseResult();
        exerciseResult.setId(this.id);
        exerciseResult.setWorkout(this.workout);
        exerciseResult.setExercise(this.exercise);
        exerciseResult.setLoad(this.load);
        exerciseResult.setReps(this.reps);
        exerciseResult.setTimeInSeconds(this.timeInSeconds);
        exerciseResult.setDayOfTraining(this.dayOfTraining);
        return exerciseResult;
    }

}
