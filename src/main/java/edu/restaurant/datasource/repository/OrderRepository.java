package edu.restaurant.datasource.repository;

import edu.restaurant.datasource.entities.Order;
import edu.restaurant.datasource.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Modifying
    @Query("UPDATE Order u SET u.status = :#{#order.status} WHERE u.id = :#{#order.id}")
    void update(Order order);

    @Query("SELECT c FROM Order c WHERE c.customerId = :customerId")
    List<Order> findAllByCustomerId(int customerId);
}
