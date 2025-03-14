package com.ecommerce.project.Service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private List<Category> categories =  new ArrayList<>();

    private Long nextId = 1L;

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
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        if(category != null) {
            categories.remove(category);
            return "Category with categoryID: " + categoryID + " deleted Successfully.";
        }else {
            return "Category with categoryID: " + categoryID + " does not exist.";
        }
    }
}
