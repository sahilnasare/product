package com.AllKindOfProduct.product.Repository;

import com.AllKindOfProduct.product.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //JPA gives us basic CRUD (findAll, save, deleteById, etc.)
}
