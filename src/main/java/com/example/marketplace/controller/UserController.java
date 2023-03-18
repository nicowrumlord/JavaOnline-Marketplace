package com.example.marketplace.controller;

import com.example.marketplace.models.User;
import com.example.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/userRegister")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User usr){
        return userService.saveDetails(usr);
    }
}
