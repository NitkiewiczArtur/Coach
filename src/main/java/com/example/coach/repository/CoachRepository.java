package com.example.coach.repository;


import com.example.coach.model.Coach;
import com.example.coach.model.Exercise;
import com.example.coach.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CoachRepository extends JpaRepository<Coach, Long> {
    Coach findByLogin(String login);

}
