package com.blog;

import com.blog.entities.User;
import com.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogSpringBootApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(BlogSpringBootApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
 //userRepository.save(new User(null,"user1234","fname","lname","mail","password","0987873",false));
    }
}
