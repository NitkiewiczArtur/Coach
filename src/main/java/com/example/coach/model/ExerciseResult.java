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

    public Long getId() {
        return id;
    }


    public Workout getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Workout workouts) {
        this.workouts = workouts;
    }

    public Exercise getExercises() {
        return exercises;
    }

    public void setExercises(Exercise exercises) {
        this.exercises = exercises;
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

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public Date getDayOfTraining() {
        return dayOfTraining;
    }

    public void setDayOfTraining(Date dayOfTraining) {
        this.dayOfTraining = dayOfTraining;
    }
}
