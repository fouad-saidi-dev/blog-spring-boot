package com.blog.aspect;

import com.blog.annotations.Owner;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.UserException;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@AllArgsConstructor
public class OwnerAspect {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    /*@Pointcut("@annotation(owner) && args(postId,..)")
    public void ownerAnnotation(Owner owner,String postId) {
    }*/

    @Before("@annotation(com.blog.annotations.Owner) && args(postId,..)")
    public void checkUserOfPost(String postId){
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        User currentUser = userRepository.findByEmail(currentUserEmail);

        Post post = postRepository.findByPostId(postId);

        if (currentUser == null || !Objects.equals(currentUser.getId(), post.getUser().getId())) {
            throw new UserException("not owner user");
        }
    }
}
