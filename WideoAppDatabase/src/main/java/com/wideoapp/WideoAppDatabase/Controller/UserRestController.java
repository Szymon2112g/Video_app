package com.wideoapp.WideoAppDatabase.Controller;

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
@CrossOrigin( origins = {"http://localhost:4200", "http://localhost:8100"})
public class UserRestController {

    private UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/get-username/{id}")
    public String getUsernameById(@PathVariable("id") int id) {
        User user = userService.findUserById(id);
        String result = user.getFirstName() + " " + user.getLastName();
        return result;
    }
}
