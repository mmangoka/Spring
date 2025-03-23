package com.test.getapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class GetApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(GetApiApplication.class, args);

        String url = "https://catfact.ninja/fact?max_length=140";

        WebClient.Builder builder = WebClient.builder();

        String catfact = String.valueOf(builder.build()
                .get().
                uri(url).
                retrieve().
                bodyToMono(CatFact.class).
                block());

            System.out.println("---------------------");
            System.out.println(catfact);
            System.out.println("---------------------");
    }

}
