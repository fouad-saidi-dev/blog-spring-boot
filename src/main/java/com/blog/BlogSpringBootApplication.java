package com.blog;

import com.blog.entities.Role;
import com.blog.entities.User;
import com.blog.repositories.RoleRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.FileService;
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
import java.time.LocalDateTime;


@SpringBootApplication
public class BlogSpringBootApplication {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    Util util;

    @Autowired
    FileService fileService;

    public static void main(String[] args) {
        SpringApplication.run(BlogSpringBootApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    public void run(String... args) throws Exception {
//        roleRepository.save(new Role(null, util.generateStringId(15),"admin", LocalDate.now(),LocalDate.now(),null));
//        roleRepository.save(new Role(null, util.generateStringId(15),"author", LocalDate.now(),LocalDate.now(),null));
//        roleRepository.save(new Role(null, util.generateStringId(15),"subscriber", LocalDate.now(),LocalDate.now(),null));
//    }
}
