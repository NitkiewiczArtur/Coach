package com.example.coach.DTO;

import com.example.coach.model.Exercise;
import com.example.coach.model.ExerciseResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkoutResultDTO {

    public List<ExerciseResult> exercisesResults = new ArrayList<>();

    public void addExerciseResult(ExerciseResult exerciseResult, Date workoutDay){
        exerciseResult.setDayOfTraining(workoutDay);
        this.exercisesResults.add(exerciseResult);
    }

    public WorkoutResultDTO(List<ExerciseResult> exercisesResults) {
        this.exercisesResults = exercisesResults;
    }
    public WorkoutResultDTO(){ }
    public List<ExerciseResult> getExerciseResults() {
        return exercisesResults;
    }

    public void setExercisesResults(List<ExerciseResult> exercisesResults) {
        this.exercisesResults = exercisesResults;
    }

    public List<ExerciseResult> getExercisesResults() {
        return exercisesResults;
    }
}
