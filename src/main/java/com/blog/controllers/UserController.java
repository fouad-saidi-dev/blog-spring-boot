package com.blog.controllers;

import com.blog.dto.UserDto;
import com.blog.entities.User;
import com.blog.requests.UserRequest;
import com.blog.responses.UserResponse;
import com.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/add-user")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {

        ModelMapper modelMapper = new ModelMapper();

        UserDto userDto = modelMapper.map(userRequest, UserDto.class);

        UserDto newUser = userService.addUser(userDto);

        UserResponse userResponse = modelMapper.map(newUser, UserResponse.class);

        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

    /*public ResponseEntity<UserResponse> add(@RequestBody UserRequest userRequest){

        Map<String,UserRequest> map = new HashMap<>();

        return new ResponseEntity<>();

    }*/

    @GetMapping("/users")
    public List<UserResponse> getUsers() {

        List<UserResponse> userResponses = new ArrayList<>();

        List<UserDto> users = userService.getUsers();

        for (UserDto user : users) {
            ModelMapper modelMapper = new ModelMapper();
            UserResponse userResponse = modelMapper.map(user, UserResponse.class);
            userResponses.add(userResponse);
        }

        return userResponses;
    }


}
