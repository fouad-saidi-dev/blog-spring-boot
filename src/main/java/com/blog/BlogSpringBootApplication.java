package com.blog;

import com.blog.entities.User;
import com.blog.repositories.UserRepository;
import com.blog.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.time.LocalDate;


@SpringBootApplication
public class BlogSpringBootApplication /*implements CommandLineRunner*/ {

//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    Util util;

    //@Autowired
    //BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BlogSpringBootApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    public void run(String... args) throws Exception {
//        userRepository.save(new User(null, util.generateStringId(15), "fuser","luser","user6@gmail.com",bCryptPasswordEncoder().encode("password"),"09876476",false, Date.valueOf(LocalDate.now()),null,null));
//    }
}
