package edu.restaurant.datasource.dao.mysqldao;

import edu.restaurant.datasource.dao.DaoFactory;
import edu.restaurant.datasource.dao.MealDao;
import edu.restaurant.datasource.dao.OrderDao;
import edu.restaurant.datasource.dao.UserDao;

public class JDBCDaoFactory extends DaoFactory {

    @Override
    public UserDao createUserDao() throws Exception {
        return new JDBCUserDAO(getConnection());
    }

    @Override
    public MealDao createMealDao() throws Exception {
        return new JDBCMealDAO(getConnection());
    }

    @Override
    public OrderDao createOrderDao() throws Exception {
        return new JDBCOrderDAO(getConnection());
    }
}
