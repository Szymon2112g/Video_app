package com.wideoapp.WideoAppDatabase.Web.controller;

import com.wideoapp.WideoAppDatabase.Service.UserService;
import com.wideoapp.WideoAppDatabase.Web.Model.UserDto;
import com.wideoapp.WideoAppDatabase.Web.controller.Exception.InvalidInputException;
import com.wideoapp.WideoAppDatabase.Web.controller.Exception.NoFoundObjectException;
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

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDTO) throws InvalidInputException {

        if (userService.isUserExist(userDTO.getEmail())) {
            throw new InvalidInputException("user with this email already exist");
        }

        boolean isSuccess = userService.addUser(userDTO);

        if (!isSuccess) {
            throw new InvalidInputException("user data are invalid");
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/username/{id}")
    public ResponseEntity<?> getUsernameById(@PathVariable("id") int id) throws InvalidInputException, NoFoundObjectException {

        if (id <= 0) {
            throw new InvalidInputException("user id is invalid");
        }

        UserDto userDTO = userService.findUserById(id);

        if (userDTO == null) {
            throw new NoFoundObjectException("no found user");
        }

        String result = userDTO.getFirstName() + " " + userDTO.getLastName();

        return ResponseEntity.ok(result);
    }
}
