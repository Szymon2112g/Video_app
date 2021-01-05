package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.Web.Model.UserDto;

public interface UserService {
    public String addUser(UserDto userDTO);
    public UserDto findUserById(int id);
}
