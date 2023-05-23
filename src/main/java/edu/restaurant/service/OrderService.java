package edu.restaurant.service;

import edu.restaurant.datasource.dao.DaoFactory;
import edu.restaurant.datasource.dao.OrderDao;
import edu.restaurant.datasource.entities.Order;
import edu.restaurant.datasource.entities.OrderStatus;

import java.util.List;

public class OrderService {

    private final DaoFactory factory = DaoFactory.getInstance();

    public List<Order> findCustomerOrders(int id)  {
        try (OrderDao rep = factory.createOrderDao()) {
            return rep.getOrdersByClientId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> findOrders()  {
        try (OrderDao rep = factory.createOrderDao()) {
            return rep.getAllOrders();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void markOrderAsReady(int orderId) {
        try (OrderDao rep = factory.createOrderDao()) {
            Order order = new Order();
            order.setId(orderId);
            order.setStatus(OrderStatus.READY);
            rep.updateOrder(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void createOrder(Order order) {
        try (OrderDao rep = factory.createOrderDao()) {
            rep.addOrder(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}