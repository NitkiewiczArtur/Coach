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

}
