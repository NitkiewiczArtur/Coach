package com.example.coach.model;

import org.springframework.format.annotation.DateTimeFormat;

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

    public Long getId() {
        return id;
    }


    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public double getLoad() {
        return load;
    }

    public void setLoad(double load) {
        this.load = load;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public Integer getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(Integer timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public Date getDayOfTraining() {
        return dayOfTraining;
    }

    public void setDayOfTraining(Date dayOfTraining) {
        this.dayOfTraining = dayOfTraining;
    }
}
