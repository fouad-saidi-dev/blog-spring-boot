package com.blog.controllers;

import com.blog.dto.LikeCommentDto;
import com.blog.requests.LikeCommentRequest;
import com.blog.responses.LikeCommentResponse;
import com.blog.services.LikeCommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/likes")
public class LikeCommentController {

    @Autowired
    LikeCommentService likeCommentService;
    @PostMapping(path = "/add-like-comment/{commentId}")
    public ResponseEntity<LikeCommentResponse> addLike(@RequestBody LikeCommentRequest request, Principal principal,@PathVariable String commentId) {

        LikeCommentDto likeCommentDto = new LikeCommentDto();

        BeanUtils.copyProperties(request,likeCommentDto);
        LikeCommentDto dto = likeCommentService.addLikeToComment(likeCommentDto, principal.getName(), commentId);

        LikeCommentResponse likeCommentResponse = new LikeCommentResponse();
        BeanUtils.copyProperties(dto,likeCommentResponse);

        return new ResponseEntity<LikeCommentResponse>(likeCommentResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = "/count/like-comment/{id}")
    public ResponseEntity<Long> countLikes(@PathVariable String id) {
        Long count = likeCommentService.countLikes(id);
        return ResponseEntity.ok(count);
    }
}
