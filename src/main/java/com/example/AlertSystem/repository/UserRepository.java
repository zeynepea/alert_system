package com.example.AlertSystem.repository;


import com.example.AlertSystem.model.AlertSystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AlertSystemUser, Long> {


}
