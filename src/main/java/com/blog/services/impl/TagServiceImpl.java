package com.blog.services.impl;

import com.blog.dto.TagDto;
import com.blog.entities.Post;
import com.blog.entities.Tag;
import com.blog.repositories.PostRepository;
import com.blog.repositories.TagRepository;
import com.blog.services.TagService;
import com.blog.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    Util util;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    PostRepository postRepository;

    @Override
    public TagDto addTag(TagDto tagDto) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagDto,tag);
        tag.setTagId(util.generateStringId(15));
        Tag newTag = tagRepository.save(tag);
        TagDto dto = new TagDto();
        BeanUtils.copyProperties(newTag,dto);
        return dto;
    }

    @Override
    public List<TagDto> getTagsByPost(String postId) {

        List<TagDto> tagDtoList = new ArrayList<>();
        List<Tag> tags = tagRepository.findAllByPostId(postId);
        for (Tag tag : tags) {
            TagDto tagDto = new TagDto();
            BeanUtils.copyProperties(tag,tagDto);
            tagDtoList.add(tagDto);
        }
        return tagDtoList;
    }
}
