package com.ansproduct.controller;

import com.ansproduct.model.Product;
import com.ansproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductRepository productRepository;

    private final String uploadDir = "uploads/";

    // ======================
    // GET ALL PRODUCTS
    // ======================
    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // ======================
    // ADD PRODUCT WITH IMAGE
    // ======================
    @PostMapping
    public ResponseEntity<?> addProduct(
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam String description,
            @RequestParam MultipartFile image
    ) throws IOException {

        // Create upload folder if not exists
        File folder = new File(uploadDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Unique file name
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        String filePath = uploadDir + fileName;

        // Save file
        image.transferTo(new File(filePath));

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage(filePath);

        productRepository.save(product);

        return ResponseEntity.ok("Product added successfully");
    }

    // ======================
    // DELETE
    // ======================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}