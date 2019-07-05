package com.example.AlertSystem.Controllers;


import com.example.AlertSystem.Models.Alert;
import com.example.AlertSystem.Models.Situation;
import com.example.AlertSystem.Services.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    @PostMapping("/alerts")
    public ResponseEntity<String> addAlert(@Valid @RequestBody Alert alert) {
        alertService.addAlert(alert) ;
        return  ResponseEntity.ok("Alert successfully added!");
    }

    @GetMapping("/alerts/{name}")
    public List<Alert> getAlertByName(@PathVariable String name){
        return alertService.getAlertByName(name);
    }


    @GetMapping("/alerts")
    public List<Alert> getAlerts(){
        return alertService.getAlerts();
    }


    @GetMapping("/getNames")
    public List<String> getAlertsNames() {
        return alertService.getAlertsNames();
    }

    @DeleteMapping("/delete/{name}")
    public Long deleteAlert(@PathVariable String name){
        return alertService.deleteAlert(name);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
