package com.example.AlertSystem.repository;

import com.example.AlertSystem.model.Alert;
import com.example.AlertSystem.model.Situation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AlertRepositoryTest {

    @Autowired
    private AlertRepository alertRepository;

    @Test
    public void findByName() {
        Alert alert1 = new Alert(1l, "alertName", "url", HttpMethod.POST, 30l, 0l, new ArrayList<Situation>());
        Alert alert2 = new Alert(2l, "alertName", "url", HttpMethod.POST, 30l, 0l, new ArrayList<Situation>());
        alertRepository.save(alert1);
        alertRepository.save(alert2);
        List<Alert> alerts= new ArrayList<Alert>(Arrays.asList(new Alert(null, "alertName", "url", HttpMethod.POST, 30l, 0l, new ArrayList<Situation>()),
                new Alert(null, "alertName", "url", HttpMethod.POST, 30l, 0l, new ArrayList<Situation>())));


        List<Alert> listOfAlerts= alertRepository.findByName("alertName");

        Assert.assertEquals(2,listOfAlerts.size());



    }

    @Test
    public void deleteByName() { //first saves 2 new alerts to database then gets size of all of them
                                 //then deletes one, then check if new size is equals to (old one - 1)

        Alert alert1 = new Alert(1l, "alertName", "url", HttpMethod.POST, 30l, 0l, new ArrayList<Situation>());
        Alert alert2 = new Alert(2l, "alertName2", "url", HttpMethod.POST, 30l, 0l, new ArrayList<Situation>());
        alertRepository.save(alert1);
        alertRepository.save(alert2);
        List<Alert> alerts_in_database = alertRepository.findAll();
        Integer size = alerts_in_database.size();
        Long id = alertRepository.deleteByName("alertName");
        List<Alert> alerts = alertRepository.findAll();
        Assert.assertEquals(size-1,alerts.size());


    }

    @Test
    public void getAllNames() {
        List<Alert> alerts_in_database = alertRepository.findAll();
        List<String> names = new ArrayList<String>();
        for(Alert alert: alerts_in_database){
            names.add(alert.getName());
        }
        List<String> names_found = alertRepository.getAllNames();
        Assert.assertEquals(names.size(),names_found.size());
    }
}