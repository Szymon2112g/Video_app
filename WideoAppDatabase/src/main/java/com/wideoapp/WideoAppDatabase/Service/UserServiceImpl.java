package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.DAO.UserDAO;
import com.wideoapp.WideoAppDatabase.Entity.User;
import com.wideoapp.WideoAppDatabase.Web.Mapper.UserMapper;
import com.wideoapp.WideoAppDatabase.Web.Model.UserDto;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public boolean addUser(UserDto userDTO) {

        if (userDTO == null) {
            return false;
        }

        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            return false;
        }

        if (userDTO.getFirstName() == null || userDTO.getFirstName().isEmpty()) {
            return false;
        }

        if (userDTO.getLastName() == null || userDTO.getLastName().isEmpty()) {
            return false;
        }

        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            return false;
        }

        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        User user = userMapper.userDtoToUser(userDTO);

        userDAO.addUser(user);
        return true;
    }

    @Override
    @Transactional
    public UserDto findUserById(int id) {

        User user = userDAO.findUserById(id);

        if (user == null) {
            return null;
        }

        UserDto userDto = userMapper.userToUserDto(user);
        return userDto;
    }

    public boolean isUserExist(String email) {

        User foundUser = userDAO.findByEmail(email);

        return foundUser != null;
    }
}
