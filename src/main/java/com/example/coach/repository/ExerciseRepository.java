package com.example.coach.repository;

import com.example.coach.model.Exercise;
import com.example.coach.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findAllByWorkouts (Workout workout);
}
