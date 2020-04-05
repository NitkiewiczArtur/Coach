package com.example.coach.controller;

import com.example.coach.WebAppTestEnvironement;
import com.example.coach.model.Exercise;
import com.example.coach.model.User;
import com.example.coach.model.Workout;
import com.example.coach.modelBuilders.ExerciseBuilder;
import com.example.coach.modelBuilders.WorkoutBuilder;
import com.example.coach.service.WorkoutService;
import com.example.coach.utils.WorkoutCalculatorImpl;

import org.jboss.jandex.JandexAntTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;


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
    @DateTimeFormat(pattern="yyyy-MM-dd")
    protected Date dayOfTraining;

    @Test
    public void showWorkoutResult_shouldAddToModelWorkoutAndWorkoutResultMapAndUserAndReturnSWRView() throws Exception{
        Exercise exercise1 = new ExerciseBuilder()
                .id(1L)
                .name("exercise1")
                .build();
        Exercise exercise2 = new ExerciseBuilder()
                .id(2L)
                .name("exercise2")
                .build();
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(exercise1);
        exercises.add(exercise2);
        Map<Date, Double> workoutResultsVMap = new HashMap<>();
        dayOfTraining = new Date(2020, 10, 20);


        Workout workout = new WorkoutBuilder()
                .id(1L)
                .name("workoutName")
                .user(new User())
                .exercises(exercises)
                .build();
        workoutResultsVMap.put(dayOfTraining, 2000.0);

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) super.getPrincipal("test_user").getPrincipal();


        when(workoutCalculatorMock.getWorkoutVMap(3L, 1L)).thenReturn(workoutResultsVMap);
        when(workoutServiceMock.getWorkoutById(1L)).thenReturn(workout);


        mockMvc.perform(get("/showWorkoutResults?workoutId={id}", 1L).session(mockHttpSession))
                .andExpect(status().isOk())
                .andExpect(view().name("show-workout-results"))
                .andExpect(model().attribute("workoutToShow", hasProperty("id", is(1L))))
                .andExpect(model().attribute("surveyMap", workoutResultsVMap));

        //when(workoutCalculatorMock.getWorkoutVMap(1L, 1L)).thenReturn(workoutResultsVMap);

        verify(workoutServiceMock, times(1)).getWorkoutById(1L);
        verifyNoMoreInteractions(workoutServiceMock);
    }
}
