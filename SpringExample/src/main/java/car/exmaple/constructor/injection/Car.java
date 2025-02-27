package car.exmaple.constructor.injection;

public class Car {

    private Specifications specs;

    public Car(Specifications specs) {
        this.specs = specs;
    }

    public void displayDetails(){
        System.out.println("Car Details: " + specs.toString());
    }
}
