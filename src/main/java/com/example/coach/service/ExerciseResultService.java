package com.example.coach.service;

import com.example.coach.DTO.WorkoutResultDTO;
import com.example.coach.model.ExerciseResult;
import com.example.coach.repository.ExerciseResultRepository;
import com.example.coach.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExerciseResultService {

    private final ExerciseResultRepository exerciseResultRepository;
    private final WorkoutRepository workoutRepository;

    @Autowired
    public ExerciseResultService(ExerciseResultRepository exerciseResultRepository, WorkoutRepository workoutRepository) {
        this.exerciseResultRepository = exerciseResultRepository;
        this.workoutRepository = workoutRepository;
    }

    public List<ExerciseResult> getAllExerciseResultsByWorkoutId(Long workoutId){
        return exerciseResultRepository.getAllByWorkout_Id(workoutId);
    }

    public void addExerciseResult(ExerciseResult exRes, Long workoutId, Date date){
        exRes.setWorkout(workoutRepository.getWorkoutById(workoutId));
        exRes.setDayOfTraining(date);
        exerciseResultRepository.save(exRes);
    }
    public List<ExerciseResult> getAllByDayOfTrainingAndWorkoutId(Date dayOfTraining, Long workoutId){
        return exerciseResultRepository.getAllByDayOfTrainingAndWorkoutId(dayOfTraining, workoutId);
    }
    public List<ExerciseResult> getAllByDayOfTrainingAndWorkoutIdAndExerciseId(Date dayOfTraining, Long workoutId, Long exerciseId){
        return exerciseResultRepository.getAllByDayOfTrainingAndWorkoutIdAndExerciseId(dayOfTraining, workoutId, exerciseId);
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
    public void deleteExerciseResultForWorkout(Long workoutId){
        exerciseResultRepository.deleteExerciseResultForWorkout(workoutId);
    }
}
