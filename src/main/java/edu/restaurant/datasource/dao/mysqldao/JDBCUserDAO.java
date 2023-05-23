package edu.restaurant.datasource.dao.mysqldao;

import edu.restaurant.datasource.ConnectionPool;
import edu.restaurant.datasource.dao.UserDao;
import edu.restaurant.datasource.entities.User;
import edu.restaurant.datasource.entities.UserRole;

import java.sql.*;

public class JDBCUserDAO implements UserDao {

    private static final String QUERY_SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String QUERY_SELECT_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String QUERY_INSERT =  "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
    private static final String QUERY_UPDATE = "UPDATE users SET  email = ?, password = ? WHERE id = ?";
    private static final String QUERY_DELETE = "DELETE FROM users WHERE id = ?";
    private final Connection conn;

    public JDBCUserDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = null;

        try (PreparedStatement stmt = conn.prepareStatement(QUERY_SELECT_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Short role = rs.getShort("role");
                    String password = rs.getString("password");
                    String email = rs.getString("email");

                    user = new User(id, email, password, UserRole.fromCode(role));
                }
            }
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        User user = null;

        try (PreparedStatement stmt = conn.prepareStatement(QUERY_SELECT_BY_EMAIL)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String password = rs.getString("password");
                    short role = rs.getShort("role");

                    user = new User(id, email, password, UserRole.fromCode(role));
                }
            }
        }
        return user;
    }

    @Override
    public int addUser(User user) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getRole().getCode());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return -1;
    }

    @Override
    public void updateUser(User user) throws SQLException {;
        try (PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(QUERY_DELETE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void close() throws Exception {
        ConnectionPool.getInstance().returnConnection(conn);
    }
}
