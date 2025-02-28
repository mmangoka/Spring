package car.exmaple.Setter.Injection;

public class Specifications {

    private String make;
    private String model;



    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }


    @Override
    public String toString() {
        return "Specifications{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
