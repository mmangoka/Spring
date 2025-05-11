package com.ecommerce.project.Controller;


import com.ecommerce.project.Service.ProductService;
import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payLoad.ProductDTO;
import com.ecommerce.project.payLoad.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ProductController {

     @Autowired
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/admin/categories/{categoryID}/product")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO,
                                                 @PathVariable  Long categoryID){

       ProductDTO createProductDTO =  productService.addProduct(productDTO,categoryID);
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

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid
            @RequestBody ProductDTO productDTO,
            @PathVariable Long productId){

        ProductDTO savedproductDTO = productService.updateProduct( productId,productDTO);
        return new ResponseEntity<>(savedproductDTO,HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId){
          ProductDTO removeProductDTO = productService.deleteProduct(productId);
        return new ResponseEntity<>(removeProductDTO,HttpStatus.OK);
    }

    @PutMapping("/admin/products/{productId}/image")
    public ResponseEntity<ProductDTO>  updateProductImage(@PathVariable Long productId,
    @RequestParam("image") MultipartFile image) throws IOException {

        ProductDTO updateProductImage =  productService.updateProductImage(productId,image);
        return new ResponseEntity<>(updateProductImage,HttpStatus.OK);
    }
}
