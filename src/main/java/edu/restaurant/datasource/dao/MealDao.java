package edu.restaurant.datasource.dao;

import edu.restaurant.datasource.entities.Meal;

import java.sql.SQLException;
import java.util.List;

public interface MealDao {
    List<Meal> getAllMeals() throws SQLException;
    Meal getMealById(int id) throws SQLException;
    int addMeal(Meal meal) throws SQLException;
    void updateMeal(Meal meal) throws SQLException;
    void deleteMeal(int id) throws SQLException;
}