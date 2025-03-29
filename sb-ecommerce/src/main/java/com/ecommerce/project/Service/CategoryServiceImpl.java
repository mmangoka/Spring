package com.ecommerce.project.Service;

import com.ecommerce.project.exceptions.APIExceptions;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    /*private List<Category> categories =  new ArrayList<>();*/

    @Autowired
   private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
         List<Category>  listOfCategories =  new ArrayList<>();
         listOfCategories.addAll(categoryRepository.findAll());

         if(listOfCategories.isEmpty()) {
             throw new APIExceptions("No Category Found in the catalogue");

         }
        return listOfCategories;
    }

    @Override
    public void createCategory(Category category) {
           Category savedCategory =  categoryRepository.findByCategoryName(category.getCategoryName());
           if(savedCategory != null) {
               throw new APIExceptions("Category  with the name " + category.getCategoryName() + " already exists");
           }
           categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryID) {

        Category category = categoryRepository.findById(categoryID).
                orElseThrow(() ->  new ResourceNotFoundException("Category","CategoryId",categoryID));

            categoryRepository.delete(category);
            return "Category with categoryID: " + categoryID + " deleted Successfully.";

    }

    @Override
    public Category updateCategory(Category category,Long categoryID){


         Category savedCategory = categoryRepository.findById(categoryID).
                 orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryID));

         category.setCategoryID(categoryID);

         savedCategory = categoryRepository.save(category);
         return savedCategory;


    }
}
