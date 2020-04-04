package com.example.coach.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "workout_exercise",
            joinColumns = {@JoinColumn(name = "workout_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "exercise_id", referencedColumnName = "id")}

    )
    private List<Exercise> exercises;

    @OneToMany(mappedBy = "workout")
    private List<ExerciseResult> exerciseResults;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
