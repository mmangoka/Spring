package com.socialMedia.socialMedia.controllers;


import com.socialMedia.socialMedia.Models.SocialUser;
import com.socialMedia.socialMedia.service.SocialService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SocialController {

    @Autowired
    private SocialService socialService;


    @GetMapping("/social/users")
    public ResponseEntity<List<SocialUser>> getSocialUser() {
        return new ResponseEntity<>(socialService.getAllUsers(), HttpStatus.OK);
    }


    @PostMapping("/social/Saveusers")
    public ResponseEntity<SocialUser> saveSocialUser(@RequestBody SocialUser socialUser) {
        return new ResponseEntity<>(socialService.saveUser(socialUser), HttpStatus.OK);
    }

}
