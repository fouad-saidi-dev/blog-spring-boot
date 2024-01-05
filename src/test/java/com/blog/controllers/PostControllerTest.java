package com.blog.controllers;

import com.blog.dto.PostDto;
import com.blog.entities.Post;
import com.blog.responses.PostResponse;
import com.blog.services.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PostControllerTest {

    //@Autowired
    //TestRestTemplate testRestTemplate;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PostService postService;

    @Autowired
    ObjectMapper objectMapper;


    /*@Test
    void shouldShowPostSuccess() throws Exception {
        Post postReturn = new Post(null,"1234","title test","body test", LocalDateTime.now(),LocalDateTime.now(),null,null);
        PostDto dto = new PostDto();
        BeanUtils.copyProperties(postReturn,dto);
        Mockito.when(postService.showPost(Mockito.anyString())).thenReturn(dto);
        String postId= "1234";
        this.mockMvc.perform(get("/posts/"+postId)
                .contentType("application/json")
                //.param("1234","true")
                //.content()
                );
    }

    @Test
    void shouldAddPostSuccess() throws Exception{
        Post postReturn = new Post(null,"1234","title test","body test", LocalDateTime.now(),LocalDateTime.now(),null,null,null,null,null);
        PostDto dto = new PostDto();
        BeanUtils.copyProperties(postReturn,dto);
        Mockito.when(postService.createPost(dto,Mockito.anyString())).thenReturn(dto);
        this.mockMvc.perform(post("/posts/create")
                        .contentType("application/json")

                .content(objectMapper.writeValueAsString(new Post(null,"1234","title test","body test", LocalDateTime.now(),LocalDateTime.now(),null,null,null,null,null)))
        );

    }*/

    /*@Test
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
    }*/

}