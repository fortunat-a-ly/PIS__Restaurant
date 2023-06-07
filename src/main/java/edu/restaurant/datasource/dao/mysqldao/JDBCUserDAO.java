package edu.restaurant.datasource.dao.mysqldao;

import edu.restaurant.datasource.dao.UserDao;
import edu.restaurant.datasource.entities.User;
import edu.restaurant.datasource.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.sql.SQLException;

@Component
public class JDBCUserDAO implements UserDao {

    private UserRepository repository;

    @Autowired
    public JDBCUserDAO(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        return repository.findByEmail(email);
    }

    @Override
    public int addUser(User user) throws SQLException {
        repository.save(user);
        return user.getId();
    }

    @Override
    public void updateUser(User user) throws SQLException {;
        repository.update(user);
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        repository.deleteById(id);
    }
}
