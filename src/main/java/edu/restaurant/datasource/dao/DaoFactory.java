package edu.restaurant.datasource.dao;

public abstract class DaoFactory {

    public abstract UserDao createUserDao();
    public abstract MealDao createMealDao();
    public abstract OrderDao createOrderDao();

}