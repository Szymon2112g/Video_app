package com.wideoapp.WideoAppDatabase.Controller;

import com.wideoapp.WideoAppDatabase.DAO.UserDAO;
import com.wideoapp.WideoAppDatabase.Entity.User;
import com.wideoapp.WideoAppDatabase.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }
}
