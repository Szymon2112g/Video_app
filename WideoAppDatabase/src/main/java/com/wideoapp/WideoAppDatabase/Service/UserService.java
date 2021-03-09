package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.Web.Model.UserDto;

public interface UserService {
    boolean addUser(UserDto userDTO);
    UserDto findUserById(int id);
    boolean isUserExist(String email);
}
