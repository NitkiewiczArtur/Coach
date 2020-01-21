package com.example.coach.repository.impl;

import com.example.coach.model.User;
import com.example.coach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public class InMemoryUserRepository {
    private List<User> listOfUsers = new ArrayList<>();

    private PasswordEncoder passwordEncoder;

    public InMemoryUserRepository(){

    }

    public List<User> getAllUsers() {
        return listOfUsers;
    }

    public void saveUser(User user) {
        listOfUsers.add(user);
    }
}
