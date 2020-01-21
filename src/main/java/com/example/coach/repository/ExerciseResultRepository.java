package com.example.coach.repository;

import com.example.coach.model.ExerciseResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseResultRepository extends JpaRepository<ExerciseResult, Long> {
}
