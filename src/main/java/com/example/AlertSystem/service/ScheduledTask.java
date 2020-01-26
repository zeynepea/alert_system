package com.example.AlertSystem.service;

import com.example.AlertSystem.model.Alert;
import com.example.AlertSystem.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ScheduledTask {

    final AsyncTask asyncTask;
    final AlertRepository alertRepository;

    @Scheduled(fixedRate = 1000)
    public void update_Alert() {
        List<Alert> alerts = alertRepository.findAll();
        for (Alert alert : alerts) {
            if (alert.getTimeLeft() == 0) {
                asyncTask.sendingReq(alert);
            } else {
                alert.setTimeLeft(alert.getTimeLeft() - 1L);
                alertRepository.save(alert);
            }
        }
    }
}
