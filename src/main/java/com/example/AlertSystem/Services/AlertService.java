package com.example.AlertSystem.Services;

import com.example.AlertSystem.Models.Alert;
import com.example.AlertSystem.Repositeries.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
@RequiredArgsConstructor
public class AlertService {

    final AlertRepository alertRepository;


    public void addAlert(final Alert alert) {
        alertRepository.save(alert);
    }


    public List<Alert> getAlertByName(String name){
        return alertRepository.findByName(name);
    }

    public List<Alert> getAlerts(){

        return alertRepository.findAll();
    }

    public List<String> getAlertsNames() {
        List<String> liste = new ArrayList<>();
        liste.addAll(alertRepository.getAllNames());
        Collections.sort(liste);
        return liste;

    }

    public Long deleteAlert(String name){
        return alertRepository.deleteByName(name);
    }
}


