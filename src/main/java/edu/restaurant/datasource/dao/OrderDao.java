package edu.restaurant.datasource.dao;

import edu.restaurant.datasource.entities.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    List<Order> getAllOrders() throws SQLException;
    Order getOrderById(int id) throws SQLException;
    List<Order> getOrdersByClientId(int clientId) throws SQLException;
    public void updateOrder(Order order) throws SQLException;
    int addOrder(Order order) throws SQLException;
    void deleteOrder(int id) throws SQLException;
}
