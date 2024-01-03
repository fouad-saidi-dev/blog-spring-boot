package com.blog.services;

import com.blog.dto.UserDto;
import com.blog.entities.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    List<UserDto> getUsers();
    UserDto showUser(String userId);
    UserDto addUser(UserDto userDto);
    UserDto updateUser(String id,UserDto userDto,String email);

    UserDto deleteUser(String userId);

    UserDto getUser(String email);

}
