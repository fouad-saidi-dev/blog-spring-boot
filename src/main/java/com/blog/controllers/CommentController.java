package com.blog.controllers;

import com.blog.dto.CommentDto;
import com.blog.requests.CommentRequest;
import com.blog.responses.CommentResponse;
import com.blog.services.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;
    @PostMapping("/add")
    public ResponseEntity<CommentResponse> addComment(@RequestBody CommentRequest request, Principal principal) {

        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(request,commentDto);
        CommentDto saveComment = commentService.addComment(commentDto,principal.getName());
        CommentResponse commentResponse = new CommentResponse();
        BeanUtils.copyProperties(saveComment,commentResponse);

        return new ResponseEntity<CommentResponse>(commentResponse, HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
