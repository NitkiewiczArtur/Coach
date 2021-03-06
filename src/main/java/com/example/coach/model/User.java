package com.example.coach.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Setter
@Getter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String login;
    @OneToMany(mappedBy = "user")
    private List<Workout> workouts;
    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name="user_role",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles;

    public User() { }

    public User(String name, String surname, String password, String login) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.login = login;
    }
}
