package car.exmaple.autowire.type;

public class Car {

    private Specifications specs;

    public void setSpecs(Specifications specs) {
        this.specs = specs;
    }

    public void displayDetails(){
        System.out.println("Car Details: " + specs.toString());
    }
}
