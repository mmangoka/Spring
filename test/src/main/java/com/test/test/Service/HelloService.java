package com.test.test.Service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String sayHello(){
        return "Hello,Spring Boot!";
    }
}
