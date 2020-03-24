package com.example.coach.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "coach")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    @OneToMany(mappedBy = "coach")
    private List<User> pupils;

}
