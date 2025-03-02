package example;

import example.Vehicle;
import org.springframework.stereotype.Component;


@Component
public class Bike implements Vehicle {

    public void ride(){
        System.out.println("Riding bike here");
    }

    @Override
    public void drive(){
        System.out.println("Riding bike here");
    }
}
