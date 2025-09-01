package com.example.springsection1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NoticesController {

    @GetMapping("/notices")
    public String getNotices(){
        return "Here are the Notice details from the DB";
    }
}
