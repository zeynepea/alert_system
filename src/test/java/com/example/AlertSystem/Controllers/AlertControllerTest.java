package com.example.AlertSystem.Controllers;

import com.example.AlertSystem.Models.Alert;
import com.example.AlertSystem.Services.AlertService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AlertControllerTest {

    @Mock
    AlertService alertService;

    /*@Before   // eğer run with kullanmasaydık
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }*/
    @InjectMocks
    AlertController alertController;

    @Test
    public void addAlert() {
        // AlertService alertService = alertService = Mockito.mock(AlertService.class);

        Alert alert = new Alert(null, "alertName", "url", HttpMethod.POST, 30l, 0l, new ArrayList());
        ArgumentCaptor<Alert> alertArgumentCaptor = ArgumentCaptor.forClass(Alert.class);
        ResponseEntity<String> response = alertController.addAlert(alert);
        verify(alertService, Mockito.times(1)).addAlert(Mockito.any(Alert.class));
        verify(alertService).addAlert(alertArgumentCaptor.capture());
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }



    @Test
    public void demo_test_deleteAlert(){
        alertController.deleteAlert("alertName");
        verify(alertService).deleteAlert("alertName");
    }

    @Test
    public void check_input_and_output_type_deleteAlert(){
        Long id = 1l;
        ArgumentCaptor<String> nameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        when(alertService.deleteAlert(Mockito.anyString())).thenReturn(id);
        Long found_id=alertController.deleteAlert("alertName");
        verify(alertService).deleteAlert(nameArgumentCaptor.capture());

        Assert.assertTrue(nameArgumentCaptor.getValue().equals("alertName"));

        Assert.assertEquals(id,found_id);
        Mockito.verifyNoMoreInteractions(alertService);
        verify(alertService, Mockito.times(1)).deleteAlert("alertName");
    }

    @Test
    public void test_getAlerts_when_empty(){
        List<Alert> alerts = alertController.getAlerts();
        Assert.assertEquals(0, alerts.size());
    }

    @Test
    public void test_getAlertByName_when_empty(){
        List<Alert> alerts = alertController.getAlertByName("xx");
        Assert.assertEquals(0, alerts.size());
    }

    @Test
    public void test_getAlerts(){
        List<Alert> alerts= new ArrayList<Alert>(Arrays.asList(new Alert(null, "alertName", "url", HttpMethod.POST, 30l, 0l, new ArrayList()),
                new Alert(null, "alertName2", "url", HttpMethod.POST, 30l, 0l, new ArrayList())));
        when(alertService.getAlerts()).thenReturn(alerts);
        List<Alert> found_alerts=alertController.getAlerts();

        Assert.assertEquals(alerts,found_alerts);

        verify(alertService, Mockito.times(1)).getAlerts();
    }

    @Test
    public void test_getAlertByName(){

        List<Alert> alerts= new ArrayList<Alert>(Arrays.asList(new Alert(null, "alertName", "url", HttpMethod.POST, 30l, 0l, new ArrayList()),
                                                        new Alert(null, "alertName2", "url", HttpMethod.POST, 30l, 0l, new ArrayList())));
        ArgumentCaptor<String> nameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        when(alertService.getAlertByName(Mockito.anyString())).thenReturn(alerts);
        List<Alert> found_alerts=alertController.getAlertByName("alertName");
        verify(alertService).getAlertByName(nameArgumentCaptor.capture());

        Assert.assertTrue(nameArgumentCaptor.getValue().equals("alertName"));
        Assert.assertEquals("alertName",found_alerts.stream().findFirst().get().getName());

        Mockito.verifyNoMoreInteractions(alertService);
        verify(alertService, Mockito.times(1)).getAlertByName("alertName");
    }

    @Test
    public void getAlertsNames(){
        List<Alert> liste = new ArrayList<Alert>(Arrays.asList(new Alert(null, "alertName", "url", HttpMethod.POST, 30l, 0l, new ArrayList()),
                new Alert(null, "alertName2", "url", HttpMethod.POST, 30l, 0l, new ArrayList())));
        List<String> names = new ArrayList<String>(Arrays.asList("alertName", "alertName2"));

        when(alertService.getAlertsNames()).thenReturn(names);

        List<String> found_names = alertController.getAlertsNames();

        Assert.assertEquals(names,found_names);
        verify(alertService).getAlertsNames();




    }



}