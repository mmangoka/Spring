package com.ecommerce.project.Service;

import com.ecommerce.project.payLoad.ProductDTO;
import com.ecommerce.project.payLoad.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    ProductDTO addProduct(ProductDTO product, Long categoryID);

    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortByProduct, String sortOrder);


    ProductResponse getProductsByCategory(Long categoryId,Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProductResponse searchProductByKeyword(String keyword,Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProductDTO updateProduct(Long productID, ProductDTO product );

    ProductDTO deleteProduct(Long productID);


    ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;
}
