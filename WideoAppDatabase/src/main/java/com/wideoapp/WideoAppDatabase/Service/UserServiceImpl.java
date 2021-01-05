package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.DAO.UserDAO;
import com.wideoapp.WideoAppDatabase.Entity.User;
import com.wideoapp.WideoAppDatabase.Web.Mapper.UserMapper;
import com.wideoapp.WideoAppDatabase.Web.Model.UserDto;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public String addUser(UserDto userDTO) {
        User user = userMapper.userDtoToUser(userDTO);
        return userDAO.addUser(user);
    }

    @Override
    @Transactional
    public UserDto findUserById(int id) {
        User user = userDAO.findUserById(id);
        UserDto userDto = userMapper.userToUserDto(user);
        return userDto;
    }
}
