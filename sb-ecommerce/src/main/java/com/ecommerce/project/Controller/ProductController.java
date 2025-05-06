package com.ecommerce.project.Controller;


import com.ecommerce.project.Service.ProductService;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payLoad.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

     @Autowired
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/admin/categories/{categoryID}/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product,
                                                 @PathVariable  Long categoryID){

       ProductDTO createProductDTO =  productService.addProduct(product,categoryID);
        return new ResponseEntity<>(createProductDTO, HttpStatus.CREATED);
    }
}
