package edu.restaurant.datasource.dao.mysqldao;

import edu.restaurant.datasource.ConnectionPool;
import edu.restaurant.datasource.dao.MealDao;
import edu.restaurant.datasource.entities.Meal;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCMealDAO implements MealDao {

    private static final String QUERY_SELECT_ALL = "SELECT * FROM meals";
    private static final String QUERY_SELECT_BY_ID = "SELECT * FROM meals WHERE id = ?";
    private static final String QUERY_INSERT =  "INSERT INTO meals (name, price) VALUES (?, ?)";
    private static final String QUERY_UPDATE = "UPDATE meals SET name = ?, price = ? WHERE id = ?";
    private static final String QUERY_DELETE = "DELETE FROM meals WHERE id = ?";
    private final Connection conn;

    public JDBCMealDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Meal> getAllMeals() throws SQLException {
        List<Meal> meals = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY_SELECT_ALL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");

                Meal meal = new Meal(id, name, price);
                meals.add(meal);
            }
        }
        return meals;
    }

    @Override
    public Meal getMealById(int id) throws SQLException {
        Meal meal = null;

        try (PreparedStatement stmt = conn.prepareStatement(QUERY_SELECT_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    BigDecimal price = rs.getBigDecimal("price");

                    meal = new Meal(id, name, price);
                }
            }
        }
        return meal;
    }

    @Override
    public int addMeal(Meal meal) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, meal.getName());
            stmt.setBigDecimal(2, meal.getPrice());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    @Override
    public void updateMeal(Meal meal) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE)) {
            stmt.setString(1, meal.getName());
            stmt.setBigDecimal(2, meal.getPrice());
            stmt.setInt(3, meal.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteMeal(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(QUERY_DELETE    )) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void close() throws Exception {
        ConnectionPool.getInstance().returnConnection(conn);
    }
}

