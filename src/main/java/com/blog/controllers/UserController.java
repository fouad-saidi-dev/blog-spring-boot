package com.blog.controllers;

import com.blog.dto.UserDto;
import com.blog.entities.User;
import com.blog.requests.UserRequest;
import com.blog.responses.UserResponse;
import com.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {

        ModelMapper modelMapper = new ModelMapper();

        UserDto userDto = modelMapper.map(userRequest, UserDto.class);

        UserDto newUser = userService.createUser(userDto);

        UserResponse userResponse = modelMapper.map(newUser, UserResponse.class);

        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

}
