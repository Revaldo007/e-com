package com.ansproduct.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String image;

    private String description;

    private Boolean active = true;

    public Product() {}

    public Product(String name, Double price, String image, String description) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.active = true;
    }

    // Getters and Setters

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Boolean getActive() { return active; }

    public void setActive(Boolean active) { this.active = active; }
}