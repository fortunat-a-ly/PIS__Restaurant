package edu.restaurant.service;

import edu.restaurant.datasource.dao.DaoFactory;
import edu.restaurant.datasource.dao.UserDao;
import edu.restaurant.datasource.entities.User;
public class UserService {
    private final DaoFactory accountRepository = DaoFactory.getInstance();
    public int registerAccount(User account) throws Exception {
        try(UserDao userDao  = accountRepository.createUserDao()) {
            return userDao.addUser(account);
        }
    }

    public User getAccountByEmail(String email) throws Exception {
        try(UserDao userDao  = accountRepository.createUserDao()) {
            return userDao.getUserByEmail(email);
        }
    }
}

