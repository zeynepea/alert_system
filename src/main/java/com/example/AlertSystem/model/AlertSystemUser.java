package com.example.AlertSystem.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode(exclude = "alerts")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlertSystemUser {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    private int active;

    private String roles = "";

    private String permissions = "";



    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="alertSystemUser_id")
    private List<Alert> alerts;

    public AlertSystemUser(AlertSystemUser user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.id = user.getId();
        this.password = user.getPassword();
        this.alerts = user.getAlerts();
        this.active = user.getActive();
        this.permissions = user.getPermissions();
        this.roles = user.getRoles();
    }

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}
