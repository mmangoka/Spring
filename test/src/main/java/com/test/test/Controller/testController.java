package com.test.test.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class testController {

    int a = 0;
    int b = 17;
    int c = 3;
    int result  = 0;

    @GetMapping("/test")
    public String sayHello(){

        try {
            String name  = "Hello,Spring Boot!";

            result = a + b + c + result;

            System.out.println("Debug Point Here: result = " + result);

        }catch(Exception e){
            return "Error";
        }

        return "Hello,Spring boot " +  result;
    }
}
