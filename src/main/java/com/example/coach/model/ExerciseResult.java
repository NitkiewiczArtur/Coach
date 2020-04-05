package com.example.coach.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "exercise_result")
public class ExerciseResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = true)
    private Workout workout;
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
    @Column(name = "load")
    private double load;
    @Column(name = "reps")
    private int reps;
    @Column(name = "time_in_seconds")
    private Integer timeInSeconds;
    @Column(name = "day_of_training")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dayOfTraining;
}
