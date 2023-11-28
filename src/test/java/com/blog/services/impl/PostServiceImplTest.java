package com.blog.services.impl;

import com.blog.dto.PostDto;
import com.blog.entities.Post;
import com.blog.repositories.PostRepository;
import com.blog.services.PostService;
import com.blog.utils.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceImplTest {

    @Autowired
    private PostService postService;

    @Test
    public void shouldSavePostWithSuccess() {
        PostDto postDto = new PostDto();
        postDto.setPostId("26787kjekjTest");
        postDto.setTitle("title test");
        postDto.setBody("body test");
        PostDto savePost = postService.createPost(postDto,"email");
        assertNotNull(savePost);
    }

    @Test
    public void shouldDisplayUsers(){
        postService.allPosts();
    }

    @Test(expected = RuntimeException.class)
    public void shouldShowUserWithSuccess(){
        postService.showPost("hjjd");
    }
}