package com.example.securityDemo;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {


    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }


    //@PreAuthorize("hasRole('USER')")//tell spring authority with this role to access this end  point
    @GetMapping("/user")
    public String userEndPoint(){
        return "Hello User";
    }


   // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminEndPoint(){
        return "Hello Admin";
    }
}
