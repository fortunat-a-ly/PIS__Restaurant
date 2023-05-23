package edu.restaurant.datasource.dao;

import edu.restaurant.datasource.ConnectionPool;
import edu.restaurant.datasource.dao.mysqldao.JDBCDaoFactory;

import java.sql.Connection;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public static synchronized DaoFactory getInstance() {
        if (daoFactory == null) {
                daoFactory = new JDBCDaoFactory();
        }
        return daoFactory;
    }

    public abstract UserDao createUserDao() throws Exception;
    public abstract MealDao createMealDao() throws Exception;
    public abstract OrderDao createOrderDao() throws Exception;

    protected Connection getConnection() {
        return ConnectionPool.getInstance().getConnection();
    }

}