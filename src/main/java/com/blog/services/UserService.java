package com.blog.services;

import com.blog.dto.UserDto;
import com.blog.entities.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    List<UserDto> getUsers();

    UserDto addUser(UserDto userDto);
}
