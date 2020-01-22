package com.example.coach.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "workout_exercise",
            joinColumns = {@JoinColumn(name = "workout_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "exercise_id", referencedColumnName = "id")}
    )
    private List<Exercise> exercises;

    @OneToMany(mappedBy = "workouts")
    private List<Exercise> exerciseResults;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    public Long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public List<Exercise> getExerciseResults() {
        return exerciseResults;
    }

    public void setExerciseResults(List<Exercise> exerciseResults) {
        this.exerciseResults = exerciseResults;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
