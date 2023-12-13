package com.blog.controllers;

import com.blog.dto.LikePostDto;
import com.blog.entities.LikePost;
import com.blog.requests.LikePostRequest;
import com.blog.responses.LikeResponse;
import com.blog.services.LikePostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikePostController {

    @Autowired
    LikePostService likeService;

    @PostMapping(path = "/add/{postId}")
    public ResponseEntity<LikeResponse> addLike(@RequestBody LikePostRequest request, Principal principal,@PathVariable String postId) {

        LikePostDto likeDto1 = new LikePostDto();

        BeanUtils.copyProperties(request,likeDto1);
        LikePostDto likeDto = likeService.addLike(likeDto1,principal.getName(),postId);

        LikeResponse likeResponse = new LikeResponse();
        BeanUtils.copyProperties(likeDto,likeResponse);

        return new ResponseEntity<LikeResponse>(likeResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public List<LikeResponse> getLikes() {

        List<LikeResponse> likeResponses = new ArrayList<>();

        List<LikePostDto> likePosts = likeService.getLikes();

        for (LikePostDto postDto: likePosts) {
            LikeResponse likeResponse = new LikeResponse();
            BeanUtils.copyProperties(postDto,likeResponse);
            likeResponses.add(likeResponse);
        }

        return likeResponses;
    }

    @GetMapping(path = "/count/like/{id}")
    public ResponseEntity<Long> countLikes(@PathVariable String id) {
        Long count = likeService.countLikes(id);
        return ResponseEntity.ok(count);
    }

    @GetMapping(path = "/count/dislike/{id}")
    public ResponseEntity<Long> countDisLikes(@PathVariable Long id) {
        Long count = likeService.countDislikes(id);
        return ResponseEntity.ok(count);
    }
}
