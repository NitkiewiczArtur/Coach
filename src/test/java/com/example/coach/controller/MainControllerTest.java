package com.example.coach.controller;

import com.example.coach.model.User;
import com.example.coach.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
/*
    @MockBean
    private CoachService coachService;
    @MockBean
    private UserService userService;
    @MockBean
    private WorkoutService workoutService;
*/

    @Test
    void whenGettingMainNotLoggedInThenRedirect() throws Exception {
        mockMvc.perform(post("/main")
                .contentType("application/json")
        ).andExpect(status().is(302)).andReturn();
    }
    @Test
    void whenLoggedInReturnMainViewCorrectly() throws Exception {
        User user = Utils.createTestUser();




    }
}
