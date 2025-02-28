package car.exmaple.Setter.Injection;

public class Car {

    private Specifications specifications;

    public void setSpecifications(Specifications specifications) {
        this.specifications = specifications;
    }

    public void displayDetails(){
        System.out.println("Car Details: " + specifications.toString());
    }
}
