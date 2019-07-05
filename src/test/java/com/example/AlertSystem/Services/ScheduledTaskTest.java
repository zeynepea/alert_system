package com.example.AlertSystem.Services;

import com.example.AlertSystem.Controllers.AlertController;
import com.example.AlertSystem.Models.Alert;
import com.example.AlertSystem.Repositeries.AlertRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScheduledTaskTest {

    @Mock
    AlertRepository alertRepository;

    @Mock
    Alert alert;


    @Mock
    AsyncTask asyncTask;

    @InjectMocks
    ScheduledTask scheduledTask;



    @Test
    public void update_alert(){
        List<Alert> alerts= new ArrayList<Alert>(Arrays.asList(new Alert(null, "alertName", "url", HttpMethod.POST, 30l, 0l, new ArrayList()),
                new Alert(null, "alertName2", "url", HttpMethod.POST, 30l, 1l, new ArrayList())));
        when(alertRepository.findAll()).thenReturn(alerts);
        scheduledTask.update_Alert();
        verify(alertRepository, Mockito.times(1)).findAll();
        verify(asyncTask, Mockito.times(1)).sendingReq(any());
        verify(alertRepository,Mockito.times(1) ).save(any());



    }



}