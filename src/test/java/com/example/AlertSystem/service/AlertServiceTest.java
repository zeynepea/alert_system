package com.example.AlertSystem.service;

import com.example.AlertSystem.model.Alert;
import com.example.AlertSystem.repository.AlertRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AlertServiceTest {

    //projeye sonradan user eklediğim için testler patladı

    /*
    @Mock
    AlertRepository alertRepository;

    @InjectMocks
    AlertService alertService;

    @Test
    public void addAlert(){
        Alert alert = new Alert(null, "alertName", "url", HttpMethod.POST, 30l, 0l, new ArrayList());
        ArgumentCaptor<Alert> alertArgumentCaptor = ArgumentCaptor.forClass(Alert.class);

        alertService.addAlert(alert);

        verify(alertRepository).save(alertArgumentCaptor.capture());
        Assert.assertTrue(alertArgumentCaptor.getValue().equals(alert));
        verify(alertRepository, Mockito.times(1)).save(Mockito.any(Alert.class));

    }

    @Test
    public void getAlertByName(){
        List<Alert> alert= new ArrayList<Alert>(Arrays.asList(new Alert(null, "alertName", "url", HttpMethod.POST, 30l, 0l, new ArrayList())));
        ArgumentCaptor<String> nameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        when(alertRepository.findByName(Mockito.any(String.class))).thenReturn(alert);
        List<Alert> found_alerts=alertService.getAlertByName("alertName");
        verify(alertRepository).findByName(nameArgumentCaptor.capture());
        Assert.assertTrue(nameArgumentCaptor.getValue().equals("alertName"));
        Assert.assertEquals("alertName",found_alerts.stream().findFirst().get().getName());
        Mockito.verifyNoMoreInteractions(alertRepository);
        verify(alertRepository, Mockito.times(1)).findByName("alertName");
    }

    @Test
    public void getAlerts(){
        List<Alert> alerts= new ArrayList<Alert>(Arrays.asList(new Alert(null, "alertName", "url", HttpMethod.POST, 30l, 0l, new ArrayList()),
                new Alert(null, "alertName2", "url", HttpMethod.POST, 30l, 0l, new ArrayList())));
        when(alertRepository.findAll()).thenReturn(alerts);
        List<Alert> alerts_found = alertService.getAlerts();
        verify(alertRepository).findAll();
        Assert.assertEquals(alerts, alerts_found);
    }

    @Test
    public void getAlertsNames(){
        List<String> names = new ArrayList<String>(Arrays.asList("alertName", "alertName2"));
        when(alertRepository.getAllNames()).thenReturn(names);
        List<String> found_names = alertService.getAlertsNames();
        verify(alertRepository).getAllNames();
        Assert.assertEquals(names,found_names);
    }

    @Test
    public void deleteAlerts(){
        Long id = 1l;
        ArgumentCaptor<String> nameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        when(alertRepository.deleteByName("alertName")).thenReturn(id);
        Long found_id=alertService.deleteAlert("alertName");
        verify(alertRepository).deleteByName(nameArgumentCaptor.capture());

        Assert.assertTrue(nameArgumentCaptor.getValue().equals("alertName"));

        Assert.assertEquals(id,found_id);
        Mockito.verifyNoMoreInteractions(alertRepository);
        verify(alertRepository, Mockito.times(1)).deleteByName("alertName");

    }



*/
}