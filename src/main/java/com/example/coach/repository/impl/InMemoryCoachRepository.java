package com.example.coach.repository.impl;

import com.example.coach.model.Coach;
import com.example.coach.repository.CoachRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryCoachRepository  {
    private List<Coach> listOfCoaches = new ArrayList<>();





    public List<Coach> getAllUsers() {
        return listOfCoaches;
    }


    public void saveCoach(Coach coach) {
        listOfCoaches.add(coach);
    }
}
