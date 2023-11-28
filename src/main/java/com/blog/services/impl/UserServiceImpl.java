package com.blog.services.impl;

import com.blog.dto.PostDto;
import com.blog.dto.UserDto;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;
import com.blog.utils.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    Util util;

    @Override
    public UserDto createUser(UserDto userDto) {

        User checkUser = userRepository.findByEmail(userDto.getEmail());

        if (checkUser != null) throw new RuntimeException("user was already exist !");
        ModelMapper modelMapper = new ModelMapper();
        userDto.setUserId(util.generateStringId(15));
        userDto.setEncryptedPassword("password");
        User user = modelMapper.map(userDto, User.class);

        User newUser = userRepository.save(user);

        UserDto userDto2 = modelMapper.map(newUser, UserDto.class);

        return userDto2;
    }

    @Override
    public UserDto addUser(UserDto userDto) {

        User checkUser = userRepository.findByEmail(userDto.getEmail());

        if (checkUser != null) throw new RuntimeException("user was already exist !");
        Map<Long, User> map = new HashMap<>();

        //userDto.setUserId(util.generateStringId(15));
        //userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUserId(util.generateStringId(15));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setEncryptedPassword(bCryptPasswordEncoder.encode("password"));
        user.setPhone(userDto.getPhone());
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);

        map.put(user.getId(), user);

        UserDto savedUserDto = new UserDto();
        savedUserDto.setUserId(user.getUserId());
        savedUserDto.setFirstName(user.getFirstName());
        savedUserDto.setLastName(user.getLastName());
        savedUserDto.setEmail(user.getEmail());
        savedUserDto.setEncryptedPassword(user.getEncryptedPassword());
        savedUserDto.setPhone(user.getPhone());
        savedUserDto.setCreatedAt(user.getCreatedAt());

        return savedUserDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto,String email) {

        User currentUser = userRepository.findByEmail(email);

        User user = userRepository.findByUserId(userId);

        if (user == null) throw new UsernameNotFoundException("User not Found !" + userId);

        if (currentUser == user) {

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setUpdatedAt(LocalDateTime.now());

        }

        User userUpdated = userRepository.save(user);

        UserDto dto = new UserDto();

        BeanUtils.copyProperties(userUpdated, dto);

        return dto;
    }

    @Override
    public UserDto deleteUser(String userId) {

        User user = userRepository.findByUserId(userId);

        if (user == null) throw new UsernameNotFoundException(userId);

        userRepository.delete(user);

        return null;
    }

    @Override
    public UserDto showUser(String userId) {

        User user = userRepository.findByUserId(userId);

        if (user == null) throw new RuntimeException(userId);

        UserDto dto = new UserDto();

        BeanUtils.copyProperties(user, dto);

        return dto;
    }

    @Override
    public List<UserDto> getUsers() {

        List<UserDto> userDtoList = new ArrayList<>();

        List<User> users = userRepository.findAll();

        for (User user : users) {
            ModelMapper modelMapper = new ModelMapper();
            UserDto userDto = modelMapper.map(user, UserDto.class);
            userDtoList.add(userDto);
        }

        return userDtoList;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        /*Optional<User> checkUser = userRepository.findByEmail(email);

        org.springframework.security.core.userdetails.User springUser = null;

        if (checkUser.isEmpty()) {
            throw new UsernameNotFoundException("User with email: "+email+" not found !");
        } else {
            User user = checkUser.get();
            springUser = new org.springframework.security.core.userdetails.User(
                    email,
                    user.getEncryptedPassword(),
                    null
            );
        }
        return springUser;*/

        User user = userRepository.findByEmail(email);

        if (user == null) throw new UsernameNotFoundException(email);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPassword(), new ArrayList<>());

    }

}
