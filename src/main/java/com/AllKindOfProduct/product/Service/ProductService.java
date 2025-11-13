package com.AllKindOfProduct.product.Service;

import com.AllKindOfProduct.product.Entity.Category;
import com.AllKindOfProduct.product.Entity.Product;
import com.AllKindOfProduct.product.Repository.CategoryRepository;
import com.AllKindOfProduct.product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    public Product CreatingProduct(Product product) {
//Creating a new product and automatically calculate its final price

//        Fetch Category from Database using its ID
        Category category = categoryRepository.findById(product.getCategory().getCategoryId()).orElseThrow(()->new RuntimeException("Category not found !"));


// ✅ calculate final price using category's (discount, GST, delivery) before saving
        double finalPrice = calculateFinalPrice(product, category);
        //set final price to the product before saving
        product.setFinalPrice(finalPrice);

//        save and return product into Database
        return productRepository.save(product);
    }

//Helper method to calculate the final price of a product
    private double calculateFinalPrice(Product product, Category category) {
        if (product.getCategory()==null){
            throw new RuntimeException("Category not found for this product !");
        }
        double basePrice = product.getBasePrice(); //fetches the actual pricing

        double discount = (category.getDiscountPercent()/100.0)* basePrice;
        double afterDiscount = basePrice - discount;

        double gst = (category.getGstPercent()/100.0) * afterDiscount;
        double delivery = category.getDeliveryCharge();

        double finalPrice = afterDiscount + gst + delivery;//total
        return finalPrice;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getAllProductsByID(Long productId) {
        return productRepository.findById(productId).orElseThrow(()->new RuntimeException("Searching product ID not found !"));
    }

    public Product updatingProductINFOByID(Long productId, Product product1) {
        Product product2= productRepository.findById(productId).orElseThrow(()->new RuntimeException("Updating product ID not found !"));
        product2.setProductName(product1.getProductName());
        product2.setProductType(product1.getProductType());
        product2.setBasePrice(product1.getBasePrice());


        //re-fetch category to apply its values again
        Category category = categoryRepository.findById(product2.getCategory().getCategoryId()).orElseThrow(()->new RuntimeException("Category not found for this product"+product2));


        // recalculate final price on update
        double finalPrice = calculateFinalPrice(product2, category);
        product2.setFinalPrice(finalPrice);

        return productRepository.save(product2);
    }

    public Product deleteProductInfoByID(Long productId) {
        Product product3 = productRepository.findById(productId).orElseThrow(()->new RuntimeException("Deleting ID not found !"));
        productRepository.deleteById(productId);
        return product3;
    }
}
