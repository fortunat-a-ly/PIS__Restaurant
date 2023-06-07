package edu.restaurant.datasource.dao.mysqldao;

import edu.restaurant.datasource.dao.DaoFactory;
import edu.restaurant.datasource.dao.MealDao;
import edu.restaurant.datasource.dao.OrderDao;
import edu.restaurant.datasource.dao.UserDao;
import edu.restaurant.datasource.repository.MealRepository;
import edu.restaurant.datasource.repository.OrderRepository;
import edu.restaurant.datasource.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JpaDaoFactory extends DaoFactory {

    private final JDBCMealDAO mealDao;
    private final JDBCOrderDAO orderDao;
    private final JDBCUserDAO userDao;


    @Autowired
    public JpaDaoFactory(UserRepository userRepository, MealRepository mealRepository, OrderRepository orderRepository) {
        this.mealDao = new JDBCMealDAO(mealRepository);
        this.orderDao  = new JDBCOrderDAO(orderRepository);
        this.userDao = new JDBCUserDAO(userRepository);
    }

    @Bean
    @Override
    public UserDao createUserDao() {
        return userDao;
    }

    @Bean
    @Override
    public MealDao createMealDao() {
        return mealDao;
    }

    @Bean
    @Override
    public OrderDao createOrderDao() {
        return orderDao;
    }
}
