package com.AllKindOfProduct.product.Controller;

import com.AllKindOfProduct.product.Entity.Product;
import com.AllKindOfProduct.product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/createProduct")
    public Product methCreate(@RequestBody Product product){
        return productService.CreatingProduct(product);
    }


    @GetMapping("/getAllProduct")
    public List<Product> methGetAll(){
        return productService.getAllProducts();
    }


    @GetMapping("/getByID/{productId}")
    public Product methGetByID(@PathVariable Long productId){
        return productService.getAllProductsByID(productId);
    }


    @PutMapping("/updateProduct/{productId}")
    public Product methUpdateByID(@PathVariable Long productId, @RequestBody Product product1){
        return productService.updatingProductINFOByID(productId,product1);
    }


    @DeleteMapping("/deleteProduct/{productId}")
    public Product methDeleteByID(@PathVariable Long productId){
        return productService.deleteProductInfoByID(productId);
    }
}
