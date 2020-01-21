package com.example.coach.repository;


import com.example.coach.model.Coach;
import com.example.coach.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository{
    List<Coach> getAllUsers();
    void saveCoach(Coach coach);
}
