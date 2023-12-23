package com.blog;


import com.blog.repositories.LikePostRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.RoleRepository;
import com.blog.services.FileService;
import com.blog.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class BlogSpringBootApplication {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    Util util;

    @Autowired
    LikePostRepository likeRepository;

    @Autowired
    FileService fileService;
    @Autowired
    PostRepository postRepository;

    public static void main(String[] args) {
        SpringApplication.run(BlogSpringBootApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ApplicationContext applicationContext() {
        return new ApplicationContext();
    }




//    @Override
//    public void run(String... args) throws Exception {
//        roleRepository.save(new Role(null, util.generateStringId(15),"admin", LocalDate.now(),LocalDate.now(),null));
//        roleRepository.save(new Role(null, util.generateStringId(15),"author", LocalDate.now(),LocalDate.now(),null));
//        roleRepository.save(new Role(null, util.generateStringId(15),"subscriber", LocalDate.now(),LocalDate.now(),null));
//    }
}
