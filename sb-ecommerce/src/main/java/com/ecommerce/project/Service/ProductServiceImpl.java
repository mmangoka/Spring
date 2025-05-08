package com.ecommerce.project.Service;


import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payLoad.ProductDTO;
import com.ecommerce.project.payLoad.ProductResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ProductDTO addProduct(Product product, Long categoryID) {


        Category category = categoryRepository.findById(categoryID).
                orElseThrow(()-> new ResourceNotFoundException("Category","categoryID","Category not found"))   ;

        product.setCategory(category);
        double specialPrice = product.getPrice() -
                ((product.getDiscount() * 0.01) * product.getPrice());

        product.setImage("default.png");
        Product savedProduct = productRepository.save(product);
        product.setSpecialProductPrice(specialPrice);


        return mapper.map(savedProduct,ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream().
                    map(product -> mapper.map(product,ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse getProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(()-> new ResourceNotFoundException("Category","categoryId","Category not found"));

        List<Product> products = productRepository.findByCategoryOrderByPriceAsc(category);

        List<ProductDTO>  productDTO = products.stream().
                map(product -> mapper.map(product,ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTO);
        return productResponse;

    }


    @Override
    public ProductResponse searchProductByKeyword(String keyword) {

        List<Product> products = productRepository.findByProductNameLikeIgnoreCase('%' + keyword + '%');

        List<ProductDTO>  productDTO = products.stream().
                map(product -> mapper.map(product,ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTO);
        return productResponse;
    }


}
