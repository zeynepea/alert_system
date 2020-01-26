package com.example.AlertSystem.controller;


import com.example.AlertSystem.model.Alert;
import com.example.AlertSystem.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/alerts")
public class AlertController {

    private final AlertService alertService;

    @PostMapping("/{username}")
    public ResponseEntity<String> addAlert(@PathVariable String username, @Valid @RequestBody Alert alert) {
        alertService.addAlert(alert, username) ;
        return  ResponseEntity.ok("Alert successfully added!");
    }

    @GetMapping("/{username}/{alertName}")
    public List<Alert> getAlertByName(@PathVariable String username, @PathVariable String alertName){
        return alertService.getAlertByName(username, alertName);
    }


    @GetMapping
    public List<Alert> getAlerts(){
        return alertService.getAlerts();
    }


    @GetMapping("/getNames/{username}")
    public List<String> getAlertsNames(@PathVariable  String username) {
        return alertService.getAlertsNames(username);
    }

    @DeleteMapping("/delete/{username}/{alertName}")
    public Long deleteAlert(@PathVariable String username,@PathVariable String alertName){
        return alertService.deleteAlert(username, alertName);
    }



    @ResponseStatus(HttpStatus.OK)
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

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public String uniqueName() {
        return "alert with the given name already exists";
    }
}
