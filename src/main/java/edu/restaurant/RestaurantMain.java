package edu.restaurant;

import edu.restaurant.datasource.ConnectionPool;
import edu.restaurant.datasource.dao.DaoFactory;
import edu.restaurant.datasource.dao.MealDao;
import edu.restaurant.datasource.dao.OrderDao;
import edu.restaurant.datasource.dao.UserDao;
import edu.restaurant.datasource.entities.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class RestaurantMain {

    public static void main(String[] args) {
        try (MealDao mealDao = DaoFactory.getInstance().createMealDao();
                UserDao userDao = DaoFactory.getInstance().createUserDao();
                OrderDao orderDao = DaoFactory.getInstance().createOrderDao()) {

            Random rand = new Random();
            //User admin = new User("admin@gmail.com", "pass", UserRole.ADMINISTRATOR);
            User customer = new User("customer" + rand.nextInt() % 10000 + "@gmail.com", "password", UserRole.CUSTOMER);
            //int adminId = userDao.addUser(admin);
            int customerId = userDao.addUser(customer);

            Meal pasta = new Meal("pasta", BigDecimal.TEN);
            Meal bread = new Meal("bread", BigDecimal.ONE);
            int pastaId = mealDao.addMeal(pasta);
            int breadId = mealDao.addMeal(bread);

            Order order1 = new Order(pastaId, customerId, OrderStatus.PREPARING);
            Order order2 = new Order(breadId, customerId, OrderStatus.PREPARING);
            int pastaOrderId = orderDao.addOrder(order1);
            int breadOrderId = orderDao.addOrder(order2);

            System.out.println(customerId + " " + userDao.getUserById(customerId));
            System.out.println(breadId + " " +mealDao.getMealById(breadId));
            System.out.println(pastaOrderId + " " + orderDao.getOrderById(pastaOrderId));

            List<Meal> meals = mealDao.getAllMeals();
            for(Meal m : meals) {
                System.out.println(m);
            }

            List<Order> orders = orderDao.getAllOrders();
            for(Order o : orders) {
                System.out.println(o);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().closeAllConnections();
        }
    }
}
