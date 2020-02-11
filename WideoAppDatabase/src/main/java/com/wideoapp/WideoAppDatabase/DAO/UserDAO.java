package com.wideoapp.WideoAppDatabase.DAO;

import com.wideoapp.WideoAppDatabase.Entity.User;

import java.util.List;

public interface UserDAO {

    public List<User> findAll();

    public String addUser(User user);
}
