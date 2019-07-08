package com.example.AlertSystem.repository;

import com.example.AlertSystem.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByName(String name);

    @Transactional
    Long deleteByName(String firstName);

    @Query("select x.name from Alert x")
    List<String> getAllNames();
}
