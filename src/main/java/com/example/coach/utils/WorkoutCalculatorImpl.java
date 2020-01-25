package com.example.coach.utils;

import com.example.coach.model.ExerciseResult;
import com.example.coach.service.ExerciseResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class WorkoutCalculatorImpl implements WorkoutCalculator{
    @Autowired
    private ExerciseResultService exerciseResultService;
    @Autowired
    public WorkoutCalculatorImpl(ExerciseResultService exerciseResultService){
        this.exerciseResultService = exerciseResultService;
    }
    public WorkoutCalculatorImpl(){ }

    public Double countTrainingVforSpecificDay(Date day){
        List<ExerciseResult> exerciseResults = exerciseResultService.getAllByDayOfTraining(day);
        Double tempV=0.0;
        for ( ExerciseResult result : exerciseResults) {
            tempV += (result.getReps()* result.getLoad());
        }
        return tempV;
    }
    public Map<Date, Double> getWorkoutVMap(Long userId){
        List<Date> daysOfTrainings = exerciseResultService.getExResDatesForUserId(userId);
        Map<Date, Double> resultMap = new HashMap<>();
        for(Date day : daysOfTrainings){
            resultMap.put(day,countTrainingVforSpecificDay(day));
        }
        return resultMap;
    }


}
