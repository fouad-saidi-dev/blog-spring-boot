package com.blog.services;

import com.blog.dto.TagDto;

import java.util.List;

public interface TagService {

    TagDto addTag(TagDto tagDto);
    List<TagDto> getTagsByPost(String postId);
}
