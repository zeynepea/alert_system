package com.example.AlertSystem.service;


import com.example.AlertSystem.model.AlertSystemUser;
import com.example.AlertSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addUser(final AlertSystemUser user){
        userRepository.save(user);
    }

    public List<AlertSystemUser> getUsers(){
        return userRepository.findAll();
    }


}
