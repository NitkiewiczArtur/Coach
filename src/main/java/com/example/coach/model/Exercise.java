package com.example.coach.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "exercises")
    private List<Workout> workouts;
    @OneToMany(mappedBy = "exercises")
    private List<ExerciseResult> exerciseResults;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public List<ExerciseResult> getExerciseResults() {
        return exerciseResults;
    }

    public void setExerciseResults(List<ExerciseResult> exerciseResults) {
        this.exerciseResults = exerciseResults;
    }
}
