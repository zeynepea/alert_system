package com.example.AlertSystem.service;

import com.example.AlertSystem.model.Alert;
import com.example.AlertSystem.repository.AlertRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AsyncTaskTest {

    @Spy
    private RestTemplate restTemplate = new RestTemplate();

    @Mock
    AlertRepository alertRepository;

    @Mock
    Alert alert;

    @InjectMocks
    AsyncTask asyncTask;

    @Test
    public void sendingReq(){
        Alert alert=new Alert(null, "google", "https://www.google.com/", HttpMethod.GET, 30l, 0l, new ArrayList());
        when(restTemplate.exchange(
                "https://www.google.com/",
                HttpMethod.GET,
                null,
                String.class)).thenCallRealMethod();
        asyncTask.sendingReq(alert);
        verify(restTemplate, Mockito.times(2)).exchange("https://www.google.com/", HttpMethod.GET, null, String.class);
        verify(alertRepository, Mockito.times(1)).save(any());
    }

    @Test
    public void sendingReq_check_if_getSituations_called_only_once(){
        ResponseEntity<String> myEntity = new ResponseEntity<String>(HttpStatus.ACCEPTED);
        Mockito.when(restTemplate.exchange(
                ArgumentMatchers.any(), ArgumentMatchers.any(HttpMethod.class),  ArgumentMatchers.<HttpEntity<String>> any(),  ArgumentMatchers.<Class<String>> any())
        ).thenReturn(myEntity);
        asyncTask.sendingReq(alert);

        verify(alert, Mockito.times(1)).getSituations();
    }


}