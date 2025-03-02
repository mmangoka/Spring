package example;

import example.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component /*not needed to be defined in xml similar to  <bean id="vehicle"
class="example.Car"  annotation based configuration>
</bean>*/
public class Car implements Vehicle {
  @Autowired
    private Tyre tyre;

    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }

    public void drive(){
        System.out.println("The car is using " + tyre);
    }
}
