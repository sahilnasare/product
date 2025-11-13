package com.AllKindOfProduct.product.Service;

import com.AllKindOfProduct.product.Entity.Category;
import com.AllKindOfProduct.product.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category newCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategoriesINFO() {
        return categoryRepository.findAll();
    }

    public Category getCategoryINFObyID(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Searching category ID not found !"));
    }

    public Category updateCategoryINFObyID(Long categoryId, Category category1) {
        Category category2 = categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Searching Category ID not found !"));
        category2.setCategoryName(category1.getCategoryName());
        category2.setDeliveryCharge(category1.getDeliveryCharge());
        category2.setDiscountPercent(category1.getDiscountPercent());
        category2.setGstPercent(category1.getGstPercent());
        return categoryRepository.save(category2);
    }

    public Category deleteCategoryINFObyID(Long categoryId) {
        Category category3 =  categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Deleting Category ID not found !"));
        categoryRepository.deleteById(categoryId);
        return category3;
    }
}
