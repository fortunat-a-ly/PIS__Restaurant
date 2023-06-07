package edu.restaurant.service;

import edu.restaurant.datasource.dao.DaoFactory;
import edu.restaurant.datasource.dao.OrderDao;
import edu.restaurant.datasource.entities.Order;
import edu.restaurant.datasource.entities.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Component
@Transactional
public class OrderService {

    private final OrderDao rep;

    @Autowired
    public OrderService(DaoFactory daoFactory) {
        this.rep = daoFactory.createOrderDao();
    }

    public List<Order> findCustomerOrders(int id) throws SQLException {
        return rep.getOrdersByClientId(id);
    }

    public List<Order> findOrders() throws SQLException {
        return rep.getAllOrders();
    }

    public void markOrderAsReady(int orderId) throws SQLException {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(OrderStatus.READY);
        rep.updateOrder(order);
    }
    public void createOrder(Order order) throws SQLException {
        rep.addOrder(order);
    }
}