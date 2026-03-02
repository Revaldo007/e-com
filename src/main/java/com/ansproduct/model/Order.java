package com.ansproduct.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "region", nullable = false)
    private String region;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "order_date")
    private LocalDateTime orderDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // ===== Getters =====

    public Long getId() { return id; }

    public Double getTotalAmount() { return totalAmount; }

    public String getPaymentMethod() { return paymentMethod; }

    public String getRegion() { return region; }

    public OrderStatus getStatus() { return status; }

    public String getTransactionId() { return transactionId; }

    public LocalDateTime getOrderDate() { return orderDate; }

    public User getUser() { return user; }

    // ===== Setters =====

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setUser(User user) {
        this.user = user;
    }
}