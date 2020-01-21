package com.example.coach.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToMany(mappedBy = "exercises")
    private List<Workout> workouts;
    @OneToMany(mappedBy = "exercises")
    private List<ExerciseResult> exerciseResults;

}
