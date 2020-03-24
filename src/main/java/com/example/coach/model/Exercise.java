package com.example.coach.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Setter
@Getter
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
    @OneToMany(mappedBy = "exercise")
    private List<ExerciseResult> exerciseResults;


}
