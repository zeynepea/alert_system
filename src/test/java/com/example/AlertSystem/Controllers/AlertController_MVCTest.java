package com.example.AlertSystem.Controllers;

import com.example.AlertSystem.Services.AlertService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AlertController_MVCTest {

    @Mock
    AlertService alertService;

    @InjectMocks
    AlertController alertController;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void dummy() {
        mockMvc = MockMvcBuilders.standaloneSetup(alertController).build();
    }

    @Test
    public void getAlerts()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/alerts")).andExpect(status().isOk());
    }

    @Test
    public void addAlert()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/alerts")).andExpect(status().isOk());
    }
}