package com.example.springsection1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/myBalance")
    public String getMyBalance(){
        return "Here are the balance details from the DB";
    }
}
