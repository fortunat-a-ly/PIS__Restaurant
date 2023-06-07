package edu.restaurant.datasource.repository;

import edu.restaurant.datasource.entities.Meal;
import edu.restaurant.datasource.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.SQLException;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Integer> {

    @Modifying
    @Query("UPDATE Meal u SET u.name = :#{#meal.name}, u.price = :#{#meal.price} WHERE u.id = :#{#meal.id}")
    public void update(Meal meal);
}
