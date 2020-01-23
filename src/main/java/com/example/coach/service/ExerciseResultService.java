package com.example.coach.service;

import com.example.coach.model.ExerciseResult;
import com.example.coach.repository.ExerciseResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseResultService {
    @Autowired
    ExerciseResultRepository exerciseResultRepository;

    public void addExerciseResult(ExerciseResult exRes){
        exerciseResultRepository.save(exRes);
    }
}
