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
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/**",allowedHeaders = "*")
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest, Principal principal) {

        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(postRequest,postDto);

        PostDto createPost = postService.createPost(postDto, principal.getName());

        PostResponse postResponse = new PostResponse();

        BeanUtils.copyProperties(createPost,postResponse);

        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<PostResponse> getPosts() {

        List<PostResponse> postResponses = new ArrayList<>();

        List<PostDto> posts = postService.allPosts();

        for (PostDto postDto:posts) {
            PostResponse postResponse = new PostResponse();
            BeanUtils.copyProperties(postDto,postResponse);
            postResponses.add(postResponse);
        }

        return postResponses;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PostResponse> showPost(@PathVariable String id) {

        PostDto postDto = postService.showPost(id);

        PostResponse postResponse = new PostResponse();
        BeanUtils.copyProperties(postDto,postResponse);

        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable String id,@RequestBody PostRequest postRequest,Principal principal) {

        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(postRequest,postDto);

        PostDto updatedPost = postService.updatePost(postDto,id, principal.getName());

        PostResponse postResponse = new PostResponse();
        BeanUtils.copyProperties(updatedPost,postResponse);

        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deletePost(@PathVariable String id) {

        postService.deletePost(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
