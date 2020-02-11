package com.wideoapp.WideoAppDatabase.Controller;

import com.wideoapp.WideoAppDatabase.DAO.UserDAO;
import com.wideoapp.WideoAppDatabase.Entity.User;
import com.wideoapp.WideoAppDatabase.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:4200")
public class UserRestController {

    private UserService userService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        logger.warn("cos dostalo " + user.getFirstName() + " " + user.getLastName() + " " + user.getEmail() + " " + user.getPassword());

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        logger.warn("password " + user.getFirstName() + " " + user.getLastName() + " " + user.getEmail() + " " + user.getPassword());

        userService.addUser(user);

        return ResponseEntity.ok().build();
    }

}
