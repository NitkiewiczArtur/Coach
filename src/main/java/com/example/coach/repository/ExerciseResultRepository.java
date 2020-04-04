package com.example.coach.repository;

import com.example.coach.model.ExerciseResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Repository
public interface ExerciseResultRepository extends JpaRepository<ExerciseResult, Long> {
    List<ExerciseResult> getAllByWorkout_Id(Long workoutId);

    List<ExerciseResult> getAllByDayOfTrainingAndWorkoutId(Date dayOfTraining, Long workoutId);
    List<ExerciseResult> getAllByDayOfTrainingAndWorkoutIdAndExerciseId(Date dayOfTraining, Long workoutId, Long exerciseId);

    @Query("select distinct exr.dayOfTraining " +
            "from com.example.coach.model.ExerciseResult exr " +
            "where exr.workout.user.id=:userId")
    <T> List<T> getDatesByUserId(@Param("userId") Long userId, Class<T> type);
    @Transactional
    @Modifying
    @Query(
            value =
                    "DELETE FROM EXERCISE_RESULT WHERE WORKOUT_ID =(:WORKOUT_ID) ",
            nativeQuery = true)
    void deleteExerciseResultForWorkout(@Param("WORKOUT_ID") Long workoutId);
    @Transactional
    @Modifying
    @Query(
            value =
                    "DELETE FROM EXERCISE_RESULT WHERE EXERCISE_ID =(:EXERCISE_ID) ",
            nativeQuery = true)
    void deleteExerciseResultForExerciseId(@Param("EXERCISE_ID") Long exerciseId);
}