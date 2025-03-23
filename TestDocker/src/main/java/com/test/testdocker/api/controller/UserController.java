package com.test.testdocker.api.controller;


import com.test.testdocker.api.model.User;
import com.test.testdocker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public User getUser(@RequestParam int id) {
        Optional<User> user = userService.getUser(id);

        if(user.isPresent()){
            return (User) user.get();
        }

        return null;
    }
}
