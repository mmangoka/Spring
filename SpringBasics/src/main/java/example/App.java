package example;

import example.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {


        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Car obj = context.getBean("car",Car.class);
        obj.drive();


      //  Tyre t =  (Tyre) context.getBean("tyre");
      //  System.out.println(t.toString());
    }

}
