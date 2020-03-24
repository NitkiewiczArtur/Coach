package com.example.coach.repository;

import com.example.coach.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
     User getUserByLogin(String login);
     Optional<User> findByLogin(String login);
     List<User> getAllByCoachId(Long coachId);
}
