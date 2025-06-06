package com.ecommerce.project.Service;


import com.ecommerce.project.exceptions.APIExceptions;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payLoad.ProductDTO;
import com.ecommerce.project.payLoad.ProductResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;


    @Override
    public ProductDTO addProduct(ProductDTO productDTO, Long categoryID) {


        //add exceptions
        boolean isProductNotPresent = true;

        Category category = categoryRepository.findById(categoryID).
                orElseThrow(()-> new ResourceNotFoundException("Category","categoryID","Category not found"))   ;

        List<Product> products = category.getProducts();

        for(Product product : products)
        {
            if(product.getProductName().equals(productDTO.getProductName())){
                isProductNotPresent =  false;
                break;
            }

        }


        if(isProductNotPresent) {
            Product product = mapper.map(productDTO, Product.class);
            product.setCategory(category);
            double specialPrice = product.getPrice() -
                    ((product.getDiscount() * 0.01) * product.getPrice());

            product.setImage("default.png");
            Product savedProduct = productRepository.save(product);
            product.setSpecialProductPrice(specialPrice);


            return mapper.map(savedProduct, ProductDTO.class);
        }else{
            throw new APIExceptions("Product already exists");
        }
    }

    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber , pageSize, sortByAndOrder);

        //if product list is 0
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product>  products = productPage.getContent();
        List<ProductDTO> productDTOS = products.stream().
                    map(product -> mapper.map(product,ProductDTO.class)).toList();

        if(productDTOS.isEmpty()){
            throw new APIExceptions("No products found");
        }




        ProductResponse productResponse = new ProductResponse();
        productResponse.setPageNumber(productPage.getNumber());
        productResponse.setPageSize(productPage.getSize());
        productResponse.setTotalElements(productPage.getTotalElements());
        productResponse.setTotalPages(productPage.getTotalPages());
        productResponse.setLastPage(productPage.isLast());
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse getProductsByCategory(Long categoryId,Integer pageNumber, Integer pageSize,
                                                 String sortBy, String sortOrder) {

        Category category = categoryRepository.findById(categoryId).
                orElseThrow(()-> new ResourceNotFoundException("Category","categoryId","Category not found"));


        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortByAndOrder);

        Page<Product> pageDetails = productRepository.findByCategoryOrderByPriceAsc(category,pageable);

        List<Product>  products = pageDetails.getContent();

        List<ProductDTO>  productDTO = products.stream().
                map(product -> mapper.map(product,ProductDTO.class)).toList();

        if(productDTO.isEmpty()){
            throw new APIExceptions("No products found");
        }

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTO);
        productResponse.setPageNumber(pageDetails.getNumber());
        productResponse.setPageSize(pageDetails.getSize());
        productResponse.setTotalElements(pageDetails.getTotalElements());
        productResponse.setTotalPages(pageDetails.getTotalPages());
        productResponse.setLastPage(pageDetails.isLast());
        return productResponse;

    }


    @Override
    public ProductResponse searchProductByKeyword(String keyword,
                                                  Integer pageNumber, Integer pageSize,

                                                  String sortBy, String sortOrder) {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortByAndOrder);


        //if product list is 0
        Page<Product> productDetails = productRepository.findByProductNameLikeIgnoreCase('%' + keyword + '%',pageable);


        List<Product>  products = productDetails.getContent();

        List<ProductDTO>  productDTO = products.stream().
                map(product -> mapper.map(product,ProductDTO.class)).toList();

        if(productDTO.isEmpty()){
            throw new APIExceptions("No products found with keyword "+ keyword);
        }

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTO);
        productResponse.setPageNumber(productDetails.getNumber());
        productResponse.setPageSize(productDetails.getSize());
        productResponse.setTotalElements(productDetails.getTotalElements());
        productResponse.setTotalPages(productDetails.getTotalPages());
        productResponse.setLastPage(productDetails.isLast());
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Long productID, ProductDTO productDTO) {
         //get the existing product from DB
        Product  savedProduct =  productRepository.findById(productID).
                orElseThrow(()-> new ResourceNotFoundException("Product","productID","Product not found"));



        //update the product
        Product product = mapper.map(productDTO,Product.class);
        savedProduct.setProductName(product.getProductName());
        savedProduct.setProductDescription(product.getProductDescription());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setDiscount(product.getDiscount());
        savedProduct.setQuantity(product.getQuantity());
        double specialPrice = product.getPrice() -
                ((product.getDiscount() * 0.01) * product.getPrice());
         savedProduct.setSpecialProductPrice(specialPrice);

       //save the database
       Product updatedProduct = productRepository.save(savedProduct);

        return mapper.map(updatedProduct,ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productID) {

            Product removeProduct  =  productRepository.findById(productID).
                    orElseThrow(()-> new ResourceNotFoundException("Product","productID","Product not found"));

            productRepository.delete(removeProduct);

            return mapper.map(removeProduct,ProductDTO.class);

    }

    @Override
    public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {

        //get product from db
        Product productFromDB = productRepository.findById(productId).
                orElseThrow(()->new ResourceNotFoundException("Product","productId","Product not found"));

        //upload the image to server - upload to /image  directory
        //get the file name of uploaded image
        String fileName = fileService.uploadImage(path,image);


        //update the new file name to the product
        productFromDB.setImage(fileName);
        //save updated product
        Product savedproduct =  productRepository.save(productFromDB);
        //return DTO after mapping product to DTO
        return mapper.map(savedproduct,ProductDTO.class) ;
    }



}
