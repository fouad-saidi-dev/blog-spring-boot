package com.blog.aspect;

import com.blog.entities.User;
import com.blog.exceptions.UserException;
import com.blog.repositories.UserRepository;
import com.blog.security.UnAuthorizedUserAuthenticationEntryPoint;
import com.blog.services.UserService;
import com.blog.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Aspect
@Component
@AllArgsConstructor
public class AuthorizationAdminAspect {

    @Autowired
    private UserRepository userRepository;

    @Pointcut("@annotation(com.blog.annotations.Admin)")
    public void adminAnnotation() {
    }

    @Before("adminAnnotation()")
    public void checkAdminAuthorization() {

        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        User currentUser = userRepository.findByEmail(currentUserEmail);

        if (currentUser == null || !currentUser.getRole().getName().equals("admin")) {
            throw new UserException("not admin");
        }
    }


}
