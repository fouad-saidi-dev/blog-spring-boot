package com.blog.services.impl;

import com.blog.dto.UserDto;
import com.blog.entities.User;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;


    @Override
    public UserDto createUser(UserDto userDto) {

        User checkUser = userRepository.findByEmail(userDto.getEmail());

        if (checkUser != null) throw new RuntimeException("user was already exist !");

        ModelMapper modelMapper = new ModelMapper();

        User user = modelMapper.map(userDto, User.class);

        User newUser = userRepository.save(user);

        UserDto userDto2 = modelMapper.map(newUser, UserDto.class);

        return userDto2;
    }
}
