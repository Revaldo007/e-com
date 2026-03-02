package com.ansproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ansproduct.model.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrue();
}