package edu.restaurant.datasource.dao.mysqldao;

import edu.restaurant.datasource.dao.OrderDao;
import edu.restaurant.datasource.entities.Order;
import edu.restaurant.datasource.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import java.util.List;

@Component
public class JDBCOrderDAO implements OrderDao {

    private final OrderRepository repository;

    @Autowired
    JDBCOrderDAO(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public Order getOrderById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getOrdersByClientId(int customerId) {
        return repository.findAllByCustomerId(customerId);
    }

    @Override
    public int addOrder(Order order) {
        repository.save(order);
        return order.getId();
    }

    @Override
    public void updateOrder(Order order) {
        repository.update(order);
    }

    @Override
    public void deleteOrder(int id) {
        repository.deleteById(id);
    }

}
