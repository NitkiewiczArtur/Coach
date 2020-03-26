package com.example.coach.service;

import com.example.coach.model.User;
import com.example.coach.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private UserRepository userRepository;


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;

    }
    public User getUserById(Long id){
        return userRepository.getOne(id);
    }
    public void saveUser(User user){

        userRepository.save(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByLogin(String login){
       return  userRepository.getUserByLogin(login);
    }
    public List<User> getAllByCoachId(Long coachId){
        return userRepository.getAllByCoachId(coachId);
    }
}
