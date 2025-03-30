package com.ecommerce.project.Service;

import com.ecommerce.project.exceptions.APIExceptions;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payLoad.CategoryDTO;
import com.ecommerce.project.payLoad.CategoryResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    /*private List<Category> categories =  new ArrayList<>();*/

    @Autowired
   private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper = new ModelMapper();


    @Override
    public CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,
                                             String sortBy,String sortOrder) {

        /*implement sorting*/
         Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ?
                  Sort.by(sortBy).ascending()
                 :Sort.by(sortBy).descending();

        /*implement pagination*/
         Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
         Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
         List<Category> categoryList = categoryPage.getContent();

         if(categoryList.isEmpty()) {
             throw new APIExceptions("No Category Found in the catalogue");

         }

         List<CategoryDTO> categoryDTOs =  categoryList.stream()
                    .map(Category-> mapper.map(Category, CategoryDTO.class))
                            .collect(Collectors.toList());

            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setContent(categoryDTOs);
            categoryResponse.setPageNumber(categoryPage.getNumber());
            categoryResponse.setPageSize(categoryPage.getSize());
            categoryResponse.setTotalElements(categoryPage.getTotalElements());
            categoryResponse.setTotalPages(categoryPage.getTotalPages());
            categoryResponse.setLastPage(categoryPage.isLast());
            return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = mapper.map(categoryDTO, Category.class);
           Category savedCategoryDB =  categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
           if(savedCategoryDB != null) {
               throw new APIExceptions("Category  with the name " + categoryDTO.getCategoryName() + " already exists");
           }
           Category savedCategory = categoryRepository.save(category);
           return mapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryID) {

        Category category = categoryRepository.findById(categoryID).
                orElseThrow(() ->  new ResourceNotFoundException("Category","CategoryId",categoryID));


            categoryRepository.delete(category);
            return mapper.map(category, CategoryDTO.class);

    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO,Long categoryID){
         Category category = mapper.map(categoryDTO, Category.class);

         Category savedCategory = categoryRepository.findById(categoryID).
                 orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryID));

         category.setCategoryID(categoryID);

         savedCategory = categoryRepository.save(category);
         return mapper.map(savedCategory,CategoryDTO.class);


    }
}
