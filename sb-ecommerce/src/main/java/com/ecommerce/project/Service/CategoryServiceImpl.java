package com.ecommerce.project.Service;

import com.ecommerce.project.model.Category;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    private List<Category> categories =  new ArrayList<>();

    private Long nextId = 1L;

    @PostConstruct
    public void init() {
        categories.add(new Category(nextId++, "Electronics"));
        categories.add(new Category(nextId++, "Clothing"));
    }

    @Override
    public List<Category> getAllCategories() {

        return categories;
    }

    @Override
    public void createCategory(Category category) {
           category.setCategoryID(nextId++);
           categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryID) {
        Category category = categories.stream()
        .filter(c->c.getCategoryID().equals(categoryID))
                .findFirst().
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"NotFound"));


            categories.remove(category);
            return "Category with categoryID: " + categoryID + " deleted Successfully.";

    }

    @Override
    public Category updateCategory(Category category,Long categoryID){
        Optional<Category> OptionalcategoriesUpdate = categories.stream()
                .filter(c->c.getCategoryID().equals(categoryID))
                .findFirst();

         if(OptionalcategoriesUpdate.isPresent()){
             Category existingCategory = OptionalcategoriesUpdate.get();
             existingCategory.setCategoryName(category.getCategoryName());
             return existingCategory;
         }else{
             throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not found");
         }


    }
}
