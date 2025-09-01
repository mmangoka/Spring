package com.example.springsection1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    @GetMapping("/myLoans")
    public String getMyCards(){
        return "Here are the Loan details from the DB";
    }
}
