package com.example.coach.service;

import com.example.coach.model.Coach;
import com.example.coach.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CoachService {

    private final CoachRepository coachRepository;

    @Autowired
    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }


    public Coach getCoachById(Long id) {
        return coachRepository.getOne(id);
    }
    public Coach getCoachByLogin(String login){
        return coachRepository.findByLogin(login);
    }

}