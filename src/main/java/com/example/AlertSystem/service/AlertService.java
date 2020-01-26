package com.example.AlertSystem.service;

import com.example.AlertSystem.model.Alert;
import com.example.AlertSystem.model.AlertSystemUser;
import com.example.AlertSystem.repository.AlertRepository;
import com.example.AlertSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;
    private final UserRepository userRepository;


    public void addAlert(final Alert alert, final String username) {
        AlertSystemUser user = userRepository.findByUsername(username);
        user.getAlerts().add(alert);
        userRepository.save(user);
    }


    public List<Alert> getAlertByName(String username, String alertName ) {
        return userRepository.findByUsername(username).getAlerts()
                .stream()
                .filter(alert -> alert.getName().equals(alertName))
                .collect(Collectors.toList());
    }

    public List<Alert> getAlerts() {
        return alertRepository.findAll();
    }

    public List<String> getAlertsNames(String username) {
        List<String> alertNames = userRepository.findByUsername(username).getAlerts()
                .stream()
                .map(Alert::getName)
                .collect(Collectors.toList());
        Collections.sort(alertNames);
        return alertNames;
    }

    public Long deleteAlert(String username,String alertName) {
        return alertRepository.deleteByName(alertName);
    }
}


