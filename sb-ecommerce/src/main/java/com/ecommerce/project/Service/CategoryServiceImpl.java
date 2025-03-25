package com.ecommerce.project.Service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    /*private List<Category> categories =  new ArrayList<>();*/

    @Autowired
   private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
           categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryID) {

        Category category = categoryRepository.findById(categoryID).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found"));

            categoryRepository.delete(category);
            return "Category with categoryID: " + categoryID + " deleted Successfully.";

    }

    @Override
    public Category updateCategory(Category category,Long categoryID){


         Category savedCategory = categoryRepository.findById(categoryID).
                 orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found"));

         category.setCategoryID(categoryID);

         savedCategory = categoryRepository.save(category);
         return savedCategory;


    }
}
