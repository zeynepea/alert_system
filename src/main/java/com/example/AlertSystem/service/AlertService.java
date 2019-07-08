package com.example.AlertSystem.service;

import com.example.AlertSystem.model.Alert;
import com.example.AlertSystem.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;


    public void addAlert(final Alert alert) {
        alertRepository.save(alert);
    }


    public List<Alert> getAlertByName(String name) {
        return alertRepository.findByName(name);
    }

    public List<Alert> getAlerts() {

        return alertRepository.findAll();
    }

    public List<String> getAlertsNames() {
        List<String> liste = new ArrayList<>();
        liste.addAll(alertRepository.getAllNames());
        Collections.sort(liste);
        return liste;

    }

    public Long deleteAlert(String name) {
        return alertRepository.deleteByName(name);
    }
}


