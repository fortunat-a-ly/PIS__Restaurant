package edu.restaurant.datasource.dao;

import edu.restaurant.datasource.entities.User;

import java.sql.SQLException;

public interface UserDao extends AutoCloseable {
    User getUserById(int id) throws SQLException;
    User getUserByEmail(String email) throws SQLException;
    int addUser(User user) throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(int id) throws SQLException;
}
