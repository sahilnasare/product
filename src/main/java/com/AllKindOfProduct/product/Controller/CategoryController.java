package com.AllKindOfProduct.product.Controller;

import com.AllKindOfProduct.product.Entity.Category;
import com.AllKindOfProduct.product.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/createCategory")
    public Category methCreateCategory(@RequestBody Category category){
        return categoryService.newCategory(category);
    }

    @GetMapping("/getAllCategories")
    public List<Category> methGetCategories(){
        return categoryService.getAllCategoriesINFO();
    }

    @GetMapping("/getCategoryByID/{categoryId}")
    public Category methGetCATByID(@PathVariable Long categoryId){
        return categoryService.getCategoryINFObyID(categoryId);
    }

    @PutMapping("/updateCategoryByID/{categoryId}")
    public Category methUpdateCategoryByID(@PathVariable Long categoryId, @RequestBody Category category1){
        return categoryService.updateCategoryINFObyID(categoryId,category1);
    }

    @DeleteMapping("/deleteCategoryByID/{categoryId}")
    public Category methDeleteCategoryByID(@PathVariable Long categoryId){
        return categoryService.deleteCategoryINFObyID(categoryId);
    }

}
