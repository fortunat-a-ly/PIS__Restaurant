package edu.restaurant.datasource.dao.mysqldao;

import edu.restaurant.datasource.ConnectionPool;
import edu.restaurant.datasource.dao.OrderDao;
import edu.restaurant.datasource.entities.Meal;
import edu.restaurant.datasource.entities.Order;
import edu.restaurant.datasource.entities.OrderStatus;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCOrderDAO implements OrderDao {

    private static final String QUERY_SELECT_ALL = "SELECT o.*, m.name, m.price FROM orders o " +
            " JOIN meals m ON o.meal_id = m.id";
    private static final String QUERY_SELECT_BY_ID = "SELECT o.*, m.name, m.price FROM orders o " +
            " JOIN meals m ON o.meal_id = m.id" +
            " WHERE o.id = ? ";
    private static final String QUERY_SELECT_BY_CUSTOMER_ID = "SELECT o.*, m.name, m.price FROM orders o " +
            " JOIN meals m ON o.meal_id = m.id" +
            " WHERE o.customer_id = ?";
    private static final String QUERY_INSERT =  "INSERT INTO orders (meal_id, customer_id, status) VALUES (?, ?, ?)";
    private static final String QUERY_UPDATE = "UPDATE orders SET  status = ? WHERE id = ?";
    private static final String QUERY_DELETE = "DELETE FROM orders WHERE id = ?";
    private final Connection conn;

    public JDBCOrderDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY_SELECT_ALL)) {
            while (rs.next()) {
                orders.add(extractOrderFromResult(rs));
            }
        }
        return orders;
    }

    @Override
    public Order getOrderById(int id) throws SQLException {
        Order order = null;

        try (PreparedStatement stmt = conn.prepareStatement(QUERY_SELECT_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    order = extractOrderFromResult(rs);
                }
            }
        }
        return order;
    }

    @Override
    public List<Order> getOrdersByClientId(int customerId) throws SQLException {
        List<Order> orders = new ArrayList<Order>();

        try (PreparedStatement stmt = conn.prepareStatement(QUERY_SELECT_BY_CUSTOMER_ID)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    orders.add(extractOrderFromResult(rs));
                }
            }
        }
        return orders;
    }

    @Override
    public int addOrder(Order order) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getMealId());
            stmt.setInt(2, order.getCustomerId());
            stmt.setInt(3, order.getStatus().getCode());
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
    public void updateOrder(Order order) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE)) {
            stmt.setInt(1, order.getStatus().getCode());
            stmt.setInt(2, order.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteOrder(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(QUERY_DELETE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Order extractOrderFromResult(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int clientId = rs.getInt("customer_id");
        int mealId = rs.getInt("meal_id");
        int status = rs.getInt("status");
        String mealName = rs.getString("name");
        BigDecimal price = rs.getBigDecimal("price");
        Timestamp creationDate = rs.getTimestamp("creation_date");

        return new Order(id, new Meal(mealId, mealName, price), clientId, OrderStatus.fromCode(status), creationDate);
    }

    @Override
    public void close() throws Exception {
        ConnectionPool.getInstance().returnConnection(conn);
    }
}
