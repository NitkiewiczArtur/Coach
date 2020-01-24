package com.example.coach.repository;

import com.example.coach.model.ExerciseResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseResultRepository extends JpaRepository<ExerciseResult, Long> {
    List<ExerciseResult> getAllByWorkouts_Id(Long workoutId);
}
