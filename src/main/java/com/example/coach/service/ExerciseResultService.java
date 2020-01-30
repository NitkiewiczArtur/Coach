package com.example.coach.service;

import com.example.coach.DTO.WorkoutCreationDto;
import com.example.coach.DTO.WorkoutResultDTO;
import com.example.coach.model.ExerciseResult;
import com.example.coach.model.Workout;
import com.example.coach.repository.ExerciseResultRepository;
import com.example.coach.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExerciseResultService {
    @Autowired
    ExerciseResultRepository exerciseResultRepository;
    @Autowired
    WorkoutRepository workoutRepository;

    public List<ExerciseResult> getAllExerciseResultsByWorkoutId(Long workoutId){
        return exerciseResultRepository.getAllByWorkout_Id(workoutId);
    }
    public void addExerciseResult(ExerciseResult exRes, Long workoutId, Date date){
        exRes.setWorkout(workoutRepository.getWorkoutById(workoutId));
        exRes.setDayOfTraining(date);
        exerciseResultRepository.save(exRes);
    }
    public List<ExerciseResult> getAllByDayOfTraining(Date dayOfTraining){
        return exerciseResultRepository.getAllByDayOfTraining(dayOfTraining);
    }
    public List<Date> getExResDatesForUserId(Long userId){
        return exerciseResultRepository.getDatesByUserId(userId, Date.class);
    }
    public void addWorkoutResult(WorkoutResultDTO form, Long workoutId, Date workoutDay){

        for(ExerciseResult exRes : form.getExercisesResults()){
            exRes.setWorkout(workoutRepository.getWorkoutById(workoutId));
            exerciseResultRepository.save(exRes);
        }
    }
}
