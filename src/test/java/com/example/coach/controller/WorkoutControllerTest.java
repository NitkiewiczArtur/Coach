package com.example.coach.controller;

import com.example.coach.WebAppTestEnvironement;
import com.example.coach.model.Exercise;
import com.example.coach.model.User;
import com.example.coach.model.Workout;
import com.example.coach.modelBuilders.WorkoutBuilder;
import com.example.coach.service.WorkoutService;
import com.example.coach.utils.WorkoutCalculatorImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WorkoutControllerTest extends WebAppTestEnvironement {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    WorkoutService workoutServiceMock;
    @MockBean
    WorkoutCalculatorImpl workoutCalculatorMock;






    @Test
    public void showWorkoutResult_shouldAddToModelWorkoutAndWorkoutResultMapAndUserAndReturnSWRView() throws Exception{
        Exercise exercise1 = new Exercise();
        exercise1.setName("exercise1");
        Exercise exercise2 = new Exercise();
        exercise1.setName("exercise2");
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(exercise1);
        exercises.add(exercise2);
        Map<Date, Double> workoutResultsVMap = null;
        //


        Workout workout = new WorkoutBuilder()
                .id(1L)
                .name("workoutName")
                .user(new User())
                .exercises(exercises)
                .build();
        when(workoutServiceMock.getWorkoutById(1L)).thenReturn(workout);
        //when(workoutCalculatorMock.getWorkoutVMap(1L, 1L)).thenReturn(workoutResultsVMap);

        mockMvc.perform(get("/showWorkoutResults?workoutId={id}", 1L).session(mockHttpSession))
                .andExpect(status().isOk())
                .andExpect(view().name("show-workout-results"))
                .andExpect(model().attribute("workoutToShow", hasProperty("id", is(1L))));
        verify(workoutServiceMock, times(1)).getWorkoutById(1L);
        verifyNoMoreInteractions(workoutServiceMock);
    }
}
