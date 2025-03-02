package car.exmaple.autowire.name;

public class Specifications {

    private String make;
    private String model;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
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
