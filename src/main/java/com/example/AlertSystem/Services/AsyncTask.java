package com.example.AlertSystem.Services;


import com.example.AlertSystem.Models.Alert;
import com.example.AlertSystem.Models.Situation;
import com.example.AlertSystem.Repositeries.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AsyncTask {

    private final RestTemplate restTemplate;
    final AlertRepository alertRepository;

    @Async
    public void sendingReq(Alert alert) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        try {
            ResponseEntity<String> result = restTemplate.exchange(
                    alert.getUrl(),
                    alert.getRequestName(),
                    null,
                    String.class);
            Situation s1 = new Situation(null, 1, strDate, result.getStatusCode().toString());
            alert.getSituations().add(s1);
        } catch (RestClientException e) {
            Situation s2 = new Situation(null, 0, strDate, e.getMessage());
            alert.getSituations().add(s2);
        }
        alert.setTimeLeft(alert.getPeriod());
        alertRepository.save(alert);
    }
}
