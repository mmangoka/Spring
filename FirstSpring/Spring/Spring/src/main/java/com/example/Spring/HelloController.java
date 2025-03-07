package com.example.Spring;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController//speciliazed version of controller annotation
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @PostMapping("/hello")
    public String helloPost(@RequestBody String name){
        return "Hello " +  name + "!";
    }

}
