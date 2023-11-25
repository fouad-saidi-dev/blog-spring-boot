package com.blog;

import com.blog.entities.User;
import com.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class BlogSpringBootApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(BlogSpringBootApplication.class, args);
    }

    //@Bean
    //public BCryptPasswordEncoder bCryptPasswordEncoder() {
    //    return new BCryptPasswordEncoder();
    //}

    @Override
    public void run(String... args) throws Exception {
        //userRepository.save(new User(null,"user1234","fname","lname","mail","password","0987873",false));
    }
}
