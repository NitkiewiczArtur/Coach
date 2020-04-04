package com.example.coach.service;

import com.example.coach.model.User;
import com.example.coach.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceIntegrationTesst {
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @MockBean
        private UserRepository userRepository;

        @Bean
        public UserService userService() {
            return new UserService(userRepository);
        }
    }

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp(){
        User user = new User("name", "surname", "password", "usersLogin");
        Optional<User> userOptional = Optional.of(user);
        Mockito.when(userRepository.findByLogin(user.getLogin()))
                .thenReturn(userOptional);
    }
    @Test
    public void whenValidLogin_thenUserShouldBeFound(){
        String login = "usersLogin";
        User found = userService.getUserByLogin(login);
        assertThat(found.getLogin())
                .isEqualTo(login);
    }

}
