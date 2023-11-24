package com.blog.controllers;

import com.blog.dto.PostDto;
import com.blog.repositories.PostRepository;
import com.blog.requests.PostRequest;
import com.blog.responses.PostResponse;
import com.blog.services.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest, Principal principal) {

        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(postRequest,postDto);

        PostDto createPost = postService.createPost(postDto);

        PostResponse postResponse = new PostResponse();

        BeanUtils.copyProperties(createPost,postResponse);

        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.CREATED);
    }
}
