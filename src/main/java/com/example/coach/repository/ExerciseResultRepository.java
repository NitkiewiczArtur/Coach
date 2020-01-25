package com.example.coach.repository;

import com.example.coach.model.ExerciseResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ExerciseResultRepository extends JpaRepository<ExerciseResult, Long> {
    List<ExerciseResult> getAllByWorkout_Id(Long workoutId);

    List<ExerciseResult> getAllByDayOfTraining(Date dayOfTraining);

    @Query("select distinct exr.dayOfTraining " +
            "from com.example.coach.model.ExerciseResult exr " +
            "where exr.workout.user.id=:userId")
    <T> List<T> getDatesByUserId(@Param("userId") Long userId, Class<T> type);
}