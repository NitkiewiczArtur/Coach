package com.example.coach.service;

import com.example.coach.model.ExerciseResult;
import com.example.coach.repository.ExerciseResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExerciseResultService {
    @Autowired
    ExerciseResultRepository exerciseResultRepository;

    public List<ExerciseResult> getAllExerciseResultsByWorkoutId(Long workoutId){
        return exerciseResultRepository.getAllByWorkout_Id(workoutId);
    }
    public void addExerciseResult(ExerciseResult exRes){
        exerciseResultRepository.save(exRes);
    }
    public List<ExerciseResult> getAllByDayOfTraining(Date dayOfTraining){
        return exerciseResultRepository.getAllByDayOfTraining(dayOfTraining);
    }
    public List<Date> getExResDatesForUserId(Long userId){
        return exerciseResultRepository.getDatesByUserId(userId, Date.class);
    }
}
