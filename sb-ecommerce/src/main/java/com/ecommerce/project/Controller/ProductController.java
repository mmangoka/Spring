package com.ecommerce.project.Controller;


import com.ecommerce.project.Service.ProductService;
import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payLoad.ProductDTO;
import com.ecommerce.project.payLoad.ProductResponse;
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

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse>  getAllProducts()
    {
        ProductResponse productResponse = productService.getAllProducts();

        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse>  getProductsByCategory(@PathVariable Long categoryId){
        ProductResponse productResponse =  productService.getProductsByCategory(categoryId);

        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse>  getProductsByKeyword(@PathVariable String keyword){
        ProductResponse  productResponse = productService.searchProductByKeyword(keyword);
        return new ResponseEntity<>(productResponse,HttpStatus.FOUND);
    }
}
