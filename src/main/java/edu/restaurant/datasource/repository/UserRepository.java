package edu.restaurant.datasource.repository;

import edu.restaurant.datasource.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT b FROM User b WHERE b.email = :email")
    public User findByEmail(String email);
    @Modifying
    @Query("UPDATE User u SET u.password = :#{#user.password}, u.email = :#{#user.email} WHERE u.id = :#{#user.id}")
    public void update(User user);
}
