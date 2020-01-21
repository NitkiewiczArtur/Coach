package com.example.coach.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exercise_result")
public class ExerciseResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workouts;
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercises;
    @Column(name = "load")
    private double load;
    @Column(name = "reps")
    private int reps;
    @Column(name = "time_in_seconds")
    private int timeInSeconds;
    @Column(name = "day_of_training")
    private Date dayOfTraining;

}
