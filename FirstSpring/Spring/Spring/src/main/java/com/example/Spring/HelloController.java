package com.example.Spring;


import org.springframework.web.bind.annotation.*;

@RestController//speciliazed version of controller annotation
public class HelloController {


    @GetMapping("/hello/{name}/show")
    public HelloResponse helloParam(@PathVariable String name){
        return new HelloResponse("Hello," +  name);
    }

    @GetMapping("/hello")
    public HelloResponse hello(){
        return new HelloResponse("Hello World.");
    }

    @PostMapping("/hello")
    public HelloResponse helloPost(@RequestBody String name){
        return new HelloResponse("Hello " +  name + "!");
    }

}
