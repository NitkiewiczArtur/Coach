package com.example.coach.modelBuilders;

import com.example.coach.model.Exercise;
import com.example.coach.model.ExerciseResult;
import com.example.coach.model.User;
import com.example.coach.model.Workout;

import java.util.List;

public class ExerciseBuilder {

    private Long id;
    private String name;


    public ExerciseBuilder id (Long id){
        this.id = id;
        return this;
    }
    public ExerciseBuilder name (String name) {
        this.name = name;
        return this;
    }

    public Exercise build(){
        if(id == null)
            throw new IllegalStateException("name cannot be empty");

        if(name.isEmpty())
            throw new IllegalStateException("name cannot be empty");

        Exercise exercise = new Exercise();

        exercise.setId(this.id);
        exercise.setName(this.name);

        return exercise;
    }
}
