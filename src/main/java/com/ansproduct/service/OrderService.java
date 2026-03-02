package com.ansproduct.service;

import com.ansproduct.model.Order;
import com.ansproduct.model.OrderStatus;
import com.ansproduct.model.User;
import com.ansproduct.repository.OrderRepository;
import com.ansproduct.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public Order saveOrder(Order order) {

        // 1️⃣ Get logged-in user's email from JWT
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        // 2️⃣ Find user from database
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3️⃣ Attach user to order
        order.setUser(user);

        // 4️⃣ Generate transaction ID
        order.setTransactionId(UUID.randomUUID().toString());

        // 5️⃣ Set initial status → PENDING
        order.setStatus(OrderStatus.PENDING);

        // 6️⃣ Save as PENDING first
        Order savedOrder = orderRepository.save(order);

        // 🔥 Simulate payment processing (real-time style)
        try {
            Thread.sleep(1000); // simulate 1 second delay
            savedOrder.setStatus(OrderStatus.SUCCESS);
        } catch (InterruptedException e) {
            savedOrder.setStatus(OrderStatus.FAILED);
        }

        // 7️⃣ Save updated status
        return orderRepository.save(savedOrder);
    }
}