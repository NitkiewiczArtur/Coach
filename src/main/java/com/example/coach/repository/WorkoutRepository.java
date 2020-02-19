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
    void deleteById(Long id);

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

    @Transactional
    @Modifying
    @Query(
            value =
                    "DELETE FROM WORKOUT_EXERCISE WHERE WORKOUT_ID =(:WORKOUT_ID) ",
            nativeQuery = true)
    void deleteWorkoutExerciseRelationTableRecords(@Param("WORKOUT_ID") Long workoutId);

    @Transactional
    @Modifying
    @Query(
            value =
                    "DELETE FROM WORKOUT_EXERCISE WHERE EXERCISE_ID =(:EXERCISE_ID) ",
            nativeQuery = true)
    void deleteWorkoutExerciseRelationTableRecordsByExId(@Param("EXERCISE_ID") Long exerciseId);
//
//    @Transactional
//    @Modifying
//    @Query(
//            value =
//                    "DELETE EXERCISE FROM WORKOUT WHERE EXERCISE_ID =(:EXERCISE_ID) ",
//            nativeQuery = true)
//    void deleteWorkoutExerciseRelationTableRecords(@Param("WORKOUT_ID") Long workoutId);
}
