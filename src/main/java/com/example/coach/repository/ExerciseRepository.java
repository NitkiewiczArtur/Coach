package com.example.coach.repository;

import com.example.coach.model.Exercise;
import com.example.coach.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findAllByWorkouts (Workout workout);


}
