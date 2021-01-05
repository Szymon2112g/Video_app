package com.wideoapp.WideoAppDatabase.Web.controller;

import com.wideoapp.WideoAppDatabase.Service.UserService;
import com.wideoapp.WideoAppDatabase.Web.Model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin( origins = {"http://localhost:4200", "http://localhost:8100"})
public class UserRestController {

    private UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userService.addUser(userDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/username/{id}")
    public String getUsernameById(@PathVariable("id") int id) {
        UserDto userDTO = userService.findUserById(id);
        String result = userDTO.getFirstName() + " " + userDTO.getLastName();
        return result;
    }
}
