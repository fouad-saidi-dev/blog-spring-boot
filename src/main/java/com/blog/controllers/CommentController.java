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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;
    @PostMapping("/add")
    public ResponseEntity<CommentResponse> addComment(@RequestBody CommentRequest request, Principal principal) {

        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(request,commentDto);
        CommentDto saveComment = commentService.addComment(commentDto,principal.getName(),request.getPostId());
        CommentResponse commentResponse = new CommentResponse();
        BeanUtils.copyProperties(saveComment,commentResponse);

        return new ResponseEntity<CommentResponse>(commentResponse, HttpStatus.CREATED);
    }
    @GetMapping(path = "/posts/{id}")
    public List<CommentResponse> getCommentsByPostId(@PathVariable String id) {

        List<CommentResponse> commentResponses = new ArrayList<>();

        List<CommentDto> comments = commentService.showComments(id);

        for (CommentDto commentDto : comments) {
            CommentResponse response = new CommentResponse();
            BeanUtils.copyProperties(commentDto,response);
            commentResponses.add(response);
        }

        return commentResponses;
    }

    @PutMapping(path = "/edit/{id}")
    public ResponseEntity<CommentResponse> updateComment(@RequestBody CommentRequest request,@PathVariable String id,Principal email) {

        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(request,commentDto);

        CommentDto updatedComment = commentService.updateComment(commentDto,id,email.getName());

        CommentResponse commentResponse = new CommentResponse();
        BeanUtils.copyProperties(updatedComment,commentResponse);

        return new ResponseEntity<CommentResponse>(commentResponse,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable String id,Principal user) {

        commentService.deleteComment(id,user.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
