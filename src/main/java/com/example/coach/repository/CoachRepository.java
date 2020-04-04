package com.example.coach.repository;


import com.example.coach.model.Coach;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ComponentScan
public interface CoachRepository extends JpaRepository<Coach, Long> {
    Coach findByLogin(String login);

}
