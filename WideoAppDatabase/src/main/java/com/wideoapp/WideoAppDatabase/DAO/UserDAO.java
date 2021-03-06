package com.wideoapp.WideoAppDatabase.DAO;

import com.wideoapp.WideoAppDatabase.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    List<User> findAll();
    void addUser(User user);
    User findByEmail(String email);
    User findUserById(int id);
}
