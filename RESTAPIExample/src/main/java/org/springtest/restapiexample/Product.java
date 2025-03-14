package org.springtest.restapiexample;

public class Product {

    private Long Id;
    private String productName;
    private double price;

    public Product(Long productID,String productName,double price){
        this.Id=productID;
        this.productName=productName;
        this.price=price;

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
