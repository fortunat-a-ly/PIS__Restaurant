package edu.restaurant.datasource.dao.mysqldao;

import edu.restaurant.datasource.dao.MealDao;
import edu.restaurant.datasource.entities.Meal;
import edu.restaurant.datasource.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.sql.SQLException;
import java.util.List;

@Component
public class JDBCMealDAO implements MealDao {

    private MealRepository repository;

    @Autowired
    public JDBCMealDAO(MealRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Meal> getAllMeals() throws SQLException {
        return repository.findAll();
    }

    @Override
    public Meal getMealById(int id) throws SQLException {
        return repository.findById(id).orElse(null);
    }

    @Override
    public int addMeal(Meal meal) throws SQLException {
        repository.save(meal);
        return meal.getId();
    }

    @Override
    public void updateMeal(Meal meal) throws SQLException {
        repository.update(meal);
    }

    @Override
    public void deleteMeal(int id) throws SQLException {
        repository.deleteById(id);
    }

}

