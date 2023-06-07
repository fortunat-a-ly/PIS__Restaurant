package edu.restaurant.service;

import edu.restaurant.datasource.dao.DaoFactory;
import edu.restaurant.datasource.dao.UserDao;
import edu.restaurant.datasource.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(DaoFactory daoFactory) {
        this.userDao = daoFactory.createUserDao();
    }
    public int registerAccount(User account) throws Exception {
        return userDao.addUser(account);
    }

    public User getAccountByEmail(String email) throws Exception {
        return userDao.getUserByEmail(email);
    }
}

