package org.springtest.restapiexample;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products =  new ArrayList<>();
    private Long IdNext = 1L;

    public ProductController(){
        products.add(new Product(IdNext++,"Laptop",999.99));
        products.add(new Product(IdNext++,"Mouse",999.99));
        products.add(new Product(IdNext++,"KeyBoard",999.99));
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return products;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }




}
