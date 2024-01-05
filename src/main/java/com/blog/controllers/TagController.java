package com.blog.controllers;

import com.blog.dto.TagDto;
import com.blog.responses.TagResponse;
import com.blog.services.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    TagService tagService;
    @GetMapping(path = "/{postId}")
    public List<TagResponse> getTagsByPostId(@PathVariable String postId) {

        List<TagResponse> tagResponses = new ArrayList<>();

        List<TagDto> tagDtoList = tagService.getTagsByPost(postId);

        for (TagDto tagDto:tagDtoList) {
            TagResponse tagResponse = new TagResponse();
            BeanUtils.copyProperties(tagDto,tagResponse);
            tagResponses.add(tagResponse);
        }

        return tagResponses;
    }
}
