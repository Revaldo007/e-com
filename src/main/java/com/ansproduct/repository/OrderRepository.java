package com.ansproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ansproduct.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}