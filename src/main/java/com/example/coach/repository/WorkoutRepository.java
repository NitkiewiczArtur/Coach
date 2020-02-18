package com.example.coach.repository;

import com.example.coach.model.Exercise;
import com.example.coach.model.Workout;
import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//??? adnotacja?

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> getWorkoutsByUser_Id(Long usersId);
    Workout getWorkoutById(Long workoutId);
    List<Workout> getWorkoutsByExercises(Exercise exercise);
    @Transactional
    @Modifying
    @Query(
            value =
                    "insert into WORKOUT_EXERCISE values (:WORKOUT_ID  , :EXERCISE_ID)",
            nativeQuery = true)
    void insertExerciseToWorkout(@Param("WORKOUT_ID") Long WORKOUT_ID, @Param("EXERCISE_ID") Long EXERCISE_ID);
}
