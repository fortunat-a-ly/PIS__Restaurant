package edu.restaurant.service;

import edu.restaurant.datasource.dao.DaoFactory;
import edu.restaurant.datasource.dao.MealDao;
import edu.restaurant.datasource.entities.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional(isolation = Isolation.SERIALIZABLE)
public class MealService {

    private final MealDao mealDao;

    @Autowired
    public MealService(DaoFactory daoFactory) {
        this.mealDao = daoFactory.createMealDao();
    }

    public List<Meal> getAll() {
        try {
            return mealDao.getAllMeals();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return new ArrayList<>();
    }



}
