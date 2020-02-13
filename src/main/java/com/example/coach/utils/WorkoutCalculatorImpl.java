package com.example.coach.utils;

import com.example.coach.model.Exercise;
import com.example.coach.model.ExerciseResult;
import com.example.coach.repository.ExerciseRepository;
import com.example.coach.service.ExerciseResultService;
import com.example.coach.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class WorkoutCalculatorImpl implements WorkoutCalculator{

    private ExerciseResultService exerciseResultService;
    private ExerciseRepository exerciseRepository;
    private WorkoutService workoutService;

    @Autowired
    public WorkoutCalculatorImpl(ExerciseResultService exerciseResultService, ExerciseRepository exerciseRepository, WorkoutService workoutService){
        this.exerciseResultService = exerciseResultService;
        this.exerciseRepository = exerciseRepository;
        this.workoutService = workoutService;
    }
    public WorkoutCalculatorImpl(){ }
    public Double countExerciseVforSpecificDay(Date day, Long workoutId, Long exerciseId){
        List<ExerciseResult> exerciseResults = exerciseResultService.getAllByDayOfTrainingAndWorkoutIdAndExerciseId(day, workoutId, exerciseId);
        Double tempV=0.0;
        for ( ExerciseResult result : exerciseResults) {
            tempV += (result.getReps()* result.getLoad());
        }
        return tempV;
    }

    public Double countTrainingVforSpecificDay(Date day, Long workoutId){
        List<ExerciseResult> exercisesResults = exerciseResultService.getAllByDayOfTrainingAndWorkoutId(day, workoutId);
        Double tempV=0.0;
        for ( ExerciseResult result : exercisesResults) {
            tempV += (result.getReps()* result.getLoad());
        }
        return tempV;
    }

  /*  public Map<String, Map<Date, Double>> getMapOfExerciseNameAndExercisesVForEachDay(Long userId, Long workoutId){
        Map<String, Map<Date, Double>> resultMap = new HashMap<>();
        List<Date> daysOfTrainings = exerciseResultService.getExResDatesForUserId(userId);
        List<Exercise> exerciseList = exerciseRepository.findAllByWorkouts(workoutService.getWorkoutById(workoutId));

        Map<Date, Double> exResultMap = new HashMap<>();


        for (Exercise exercise : exerciseList) {
            for(Date day : daysOfTrainings) {
                exResultMap.put(day, countExerciseVforSpecificDay(day, workoutId, exercise.getId()));
            }
            resultMap.put(exercise.getName(), exResultMap);
        }
        return resultMap;
        }*/

    public Map<Date, Double> getWorkoutVMap(Long userId, Long workoutId){
        List<Date> daysOfTrainings = exerciseResultService.getExResDatesForUserId(userId);
        Map<Date, Double> resultMap = new TreeMap<>();
        for(Date day : daysOfTrainings){
            resultMap.put(day,countTrainingVforSpecificDay(day, workoutId));
        }
        return resultMap;
    }
    public Map<Date, Double> getExerciseVMap(Long userId, Long workoutId, Long exerciseId){
        List<Date> daysOfTrainings = exerciseResultService.getExResDatesForUserId(userId);
        Map<Date, Double> resultMap = new TreeMap<>();
        for(Date day : daysOfTrainings){
            resultMap.put(day,countExerciseVforSpecificDay(day, workoutId, exerciseId));
        }
        return resultMap;
    }


}
