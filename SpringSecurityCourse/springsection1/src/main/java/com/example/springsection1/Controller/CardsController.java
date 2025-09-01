package com.example.springsection1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping("/myCards")
    public String getMyCards(){
        return "Here are the card details from the DB";
    }
}
