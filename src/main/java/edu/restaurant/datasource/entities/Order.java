package edu.restaurant.datasource.entities;

import edu.restaurant.datasource.utils.CustomOrderStatusEnumConverter;
import edu.restaurant.datasource.utils.CustomUserRoleEnumConverter;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meal_id")
    private Meal meal;
    @Column(name = "customer_id")
    private int customerId;
    @Convert(converter = CustomOrderStatusEnumConverter.class)
    private OrderStatus status;
    @Column(name = "creation_date", nullable = true)
    private Timestamp timestamp;

    public Order(int mealId, int customerId, OrderStatus status, Timestamp timestamp) {
        this.meal = new Meal(mealId);
        this.customerId = customerId;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Order(int mealId, int customerId, OrderStatus status) {
        this.meal = new Meal(mealId);
        this.customerId = customerId;
        this.status = status;
    }

    public Order(int id, Meal meal, int customerId, OrderStatus status, Timestamp timestamp) {
        this.id = id;
        this.meal = meal;
        this.customerId = customerId;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Order() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMealId() {
        return meal.getId();
    }

    public void setMealId(int mealId) {
        this.meal.setId(mealId);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Meal getMeal() {
        return meal;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", meal=" + meal +
                ", customerId=" + customerId +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }

    @PrePersist
    public void setCreatedAtDefault() {
        if (this.timestamp == null) {
            this.timestamp = new Timestamp(System.currentTimeMillis());
        }
    }
}
