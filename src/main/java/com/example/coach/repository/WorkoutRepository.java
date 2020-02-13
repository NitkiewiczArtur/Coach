package com.example.coach.repository;

import com.example.coach.model.Exercise;
import com.example.coach.model.Workout;
import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//??? adnotacja?

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> getWorkoutsByUser_Id(Long usersId);
    Workout getWorkoutById(Long workoutId);
    List<Workout> getWorkoutsByExercises(Exercise exercise);
}
