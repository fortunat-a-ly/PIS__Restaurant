package edu.restaurant.datasource.entities;

import java.sql.Timestamp;

public class Order {
    private int id;
    private Meal meal;
    private int customerId;
    private OrderStatus status;
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
}
