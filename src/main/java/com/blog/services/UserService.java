package com.blog.services;

import com.blog.dto.UserDto;
import com.blog.entities.User;

public interface UserService {

    UserDto createUser(UserDto userDto);
}
