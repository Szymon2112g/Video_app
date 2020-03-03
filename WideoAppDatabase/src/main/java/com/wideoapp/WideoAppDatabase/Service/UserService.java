package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.Entity.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();

    public String addUser(User user);

    public User findByEmail(String email);

    public User findUserById(int id);

}
