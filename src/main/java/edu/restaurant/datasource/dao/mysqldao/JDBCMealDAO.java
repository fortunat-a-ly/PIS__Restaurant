package edu.restaurant.datasource.dao.mysqldao;

import edu.restaurant.datasource.dao.MealDao;
import edu.restaurant.datasource.entities.Meal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.sql.SQLException;
import java.util.List;

public class JDBCMealDAO implements MealDao {

    private EntityManager entityManager;

    public JDBCMealDAO(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public List<Meal> getAllMeals() throws SQLException {
        return entityManager.createQuery("SELECT o FROM Meal o", Meal.class)
                .getResultList();
    }

    @Override
    public Meal getMealById(int id) throws SQLException {
        return entityManager.find(Meal.class, id);
    }

    @Override
    public int addMeal(Meal meal) throws SQLException {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(meal);
        entityManager.flush();
        entityManager.refresh(meal);
        transaction.commit();
        return meal.getId();
    }

    @Override
    public void updateMeal(Meal meal) throws SQLException {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Meal existingEntity = entityManager.find(Meal.class, meal.getId());
        existingEntity.setPrice(meal.getPrice());
        entityManager.merge(existingEntity);
        transaction.commit();
    }

    @Override
    public void deleteMeal(int id) throws SQLException {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Meal entity = entityManager.find(Meal.class, id);
        entityManager.remove(entity);
        transaction.commit();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
    }
}

