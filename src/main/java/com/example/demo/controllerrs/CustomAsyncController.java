package com.example.demo.controllerrs;


import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomAsyncController {
    @Autowired
    UserService userService;

    @PostMapping("/save")
    public List<User> save(@RequestBody User user) {
        userService.save(user);
        return userService.findAll();
    }




}
