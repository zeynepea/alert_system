package com.example.AlertSystem.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @Email
    @NotBlank
    private String email;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="alertSystemUser_id")
    private List<Alert> alerts;
}
