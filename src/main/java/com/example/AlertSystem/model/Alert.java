package com.example.AlertSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "name is mandotory")
    private String name;

    @URL
    @NotBlank(message = "url is mandotory")
    @Column(length = 2048)
    private String url;

    @NotNull
    private HttpMethod requestName;

    @NotNull
    @Min(1)
    private Long period;

    @NotNull
    private Long timeLeft;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "alert_id")
    private List<Situation> situations;


}
