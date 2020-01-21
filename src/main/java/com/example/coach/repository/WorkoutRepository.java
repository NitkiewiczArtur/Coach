package com.example.coach.repository;

import com.example.coach.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//??? adnotacja?

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
