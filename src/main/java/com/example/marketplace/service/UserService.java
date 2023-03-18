package com.example.marketplace.service;

import com.example.marketplace.encrypt.MyAESapp;
import com.example.marketplace.models.User;
import com.example.marketplace.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    private MyAESapp myAESapp;

    public User saveDetails(User usr){
        if(usr.getIdUser()==null){
            return userRepo.save(usr);
        } else {
            Optional<User> auxUser = userRepo.getUser(usr.getIdUser());
            if (auxUser.isEmpty()){
                return userRepo.save(usr);
            } else {
                return usr;
            }
        }

    }
}
