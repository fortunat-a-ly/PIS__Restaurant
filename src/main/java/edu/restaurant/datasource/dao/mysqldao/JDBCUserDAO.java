package edu.restaurant.datasource.dao.mysqldao;

import edu.restaurant.datasource.dao.UserDao;
import edu.restaurant.datasource.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.SQLException;

public class JDBCUserDAO implements UserDao {

    private EntityManager entityManager;
    private static final String QUERY_SELECT_BY_EMAIL = "SELECT u FROM User u WHERE u.email = :email";

    public JDBCUserDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    @Override
    public User getUserById(int id) throws SQLException {
        return (User) entityManager.find(User.class, id);
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        TypedQuery<User> query = entityManager.createQuery(
                QUERY_SELECT_BY_EMAIL, User.class);
        query.setParameter("email", email);
        User user = query.getSingleResult();
        return user;
    }

    @Override
    public int addUser(User user) throws SQLException {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        return user.getId();
    }

    @Override
    public void updateUser(User user) throws SQLException {;
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User existingUser = entityManager.find(User.class, user.getId());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        entityManager.merge(existingUser);
        transaction.commit();
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        transaction.commit();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
    }
}
