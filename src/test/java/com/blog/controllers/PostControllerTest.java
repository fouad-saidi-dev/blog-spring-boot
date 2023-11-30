package com.blog.controllers;

import com.blog.dto.PostDto;
import com.blog.entities.Post;
import com.blog.responses.PostResponse;
import com.blog.services.PostService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    PostService postService;

    @Test
    void showPost() {
        Post postReturn = new Post(null,"1234","title test","body test", LocalDateTime.now(),LocalDateTime.now(),null,null);
        PostDto dto = new PostDto();
        BeanUtils.copyProperties(postReturn,dto);
        Mockito.when(postService.showPost(Mockito.anyString())).thenReturn(dto);

        String postId = "1234";
        ResponseEntity<PostResponse> post = testRestTemplate.getForEntity("/posts/"+postId,PostResponse.class);
        Assertions.assertThat(post.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK,post.getStatusCode());
        Assertions.assertThat(post.getBody()).isEqualTo("OK");
        //error 401 UNAUTHORIZED
    }

}