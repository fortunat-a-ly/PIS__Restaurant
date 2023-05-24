package edu.restaurant.datasource.dao.mysqldao;

import edu.restaurant.datasource.dao.OrderDao;
import edu.restaurant.datasource.entities.Order;

import javax.persistence.*;
import java.util.List;

public class JDBCOrderDAO implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    public JDBCOrderDAO(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public List<Order> getAllOrders() {
        return entityManager.createQuery("SELECT o FROM Order o JOIN FETCH o.meal", Order.class)
                .getResultList();
    }

    @Override
    public Order getOrderById(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpql = "SELECT o FROM Order o JOIN FETCH o.meal m WHERE o.id = :id";
        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);
        query.setParameter("id", id);
        transaction.commit();
        return query.getSingleResult();
    }

    @Override
    public List<Order> getOrdersByClientId(int customerId) {
        return entityManager.createQuery("SELECT o FROM Order o JOIN FETCH o.meal WHERE o.customerId = :customerId", Order.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    @Override
    public int addOrder(Order order) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(order);
        transaction.commit();
        return order.getId();
    }

    @Override
    public void updateOrder(Order order) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Order existingEntity = entityManager.find(Order.class, order.getId());
        existingEntity.setStatus(order.getStatus());
        entityManager.merge(existingEntity);
        transaction.commit();
    }

    @Override
    public void deleteOrder(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Order order = entityManager.find(Order.class, id);
        entityManager.remove(order);
        transaction.commit();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
    }
}
