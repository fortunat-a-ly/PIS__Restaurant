package edu.restaurant.service;

import edu.restaurant.datasource.dao.DaoFactory;
import edu.restaurant.datasource.dao.MealDao;
import edu.restaurant.datasource.entities.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealService {

    private final DaoFactory repository = DaoFactory.getInstance();

    public List<Meal> getAll() {
        try (MealDao mealDao = repository.createMealDao()){
            return mealDao.getAllMeals();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return new ArrayList<>();
    }



}
