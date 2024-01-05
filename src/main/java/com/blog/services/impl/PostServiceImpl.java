package com.blog.services.impl;

import com.blog.dto.CommentDto;
import com.blog.dto.PostDto;
import com.blog.dto.UserDto;
import com.blog.entities.Post;
import com.blog.entities.Tag;
import com.blog.entities.User;
import com.blog.repositories.PostRepository;
import com.blog.repositories.TagRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.PostService;
import com.blog.utils.Util;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Util util;

    @Autowired
    PostRepository postRepository;

    @Autowired
    TagRepository tagRepository;
    //private Logger log = (Logger) LoggerFactory.getLogger(PostService.class);

    @Override
    public PostDto createPost(PostDto postDto,String email,List<String> tagNames) {

        User currentUser = userRepository.findByEmail(email);

        Post post = new Post();

        BeanUtils.copyProperties(postDto, post);
        post.setPostId(util.generateStringId(15));
        post.setUser(currentUser);
        post.setCreatedAt(LocalDateTime.now());
        Post newPost = postRepository.save(post);
        // add Tags

        for (String tagName: tagNames) {
           Tag tag = tagRepository.findByName(tagName);
           if (tag == null) {
               tag = new Tag();
               tag.setName(tagName);
               tag.setTagId(util.generateStringId(15));
               tagRepository.save(tag);
           }
           newPost.getTags().add(tag);
        }

        postRepository.save(newPost);

        PostDto dto = new PostDto();
        BeanUtils.copyProperties(newPost, dto);

        return dto;
    }

    @Override
    public List<PostDto> allPosts() {

        List<PostDto> postDtos = new ArrayList<>();

        List<Post> posts = postRepository.findAll();

        for (Post post : posts
        ) {
            PostDto postDto = new PostDto();
            BeanUtils.copyProperties(post,postDto);
            postDtos.add(postDto);
        }

        return postDtos;
    }

    @Override
    public PostDto showPost(String postId) {

        Post post = postRepository.findByPostId(postId);

        if (post == null) throw new RuntimeException(postId);

        PostDto dto = new PostDto();
        BeanUtils.copyProperties(post,dto);
        return dto;
    }

    @Override
    public PostDto updatePost(PostDto postDto, String postId, String email) {

        User checkUser = userRepository.findByEmail(email);

        Post post = postRepository.findByPostId(postId);

        if (checkUser == post.getUser()) {
            post.setTitle(postDto.getTitle());
            post.setDescription(postDto.getDescription());
            post.setBody(postDto.getBody());
            post.setUpdatedAt(LocalDateTime.now());
        }
        Post postUpdated = postRepository.save(post);
        PostDto dto = new PostDto();
        BeanUtils.copyProperties(postUpdated,dto);

        return dto;
    }

    @Override
    public PostDto deletePost(String id,String email) {

        User checkUser = userRepository.findByEmail(email);

        Post post = postRepository.findByPostId(id);

        if (post == null) throw new UsernameNotFoundException(id);
        if (checkUser.getRole().getName().equals("admin") || checkUser == post.getUser()) {
            postRepository.delete(post);
        }
        return null;
    }

    @Override
    public List<PostDto> getPostsByUser(String email, String userId) {

        User checkUser = userRepository.findByEmail(email);

        User user = userRepository.findByUserId(userId);

        if (checkUser == null) throw new UsernameNotFoundException(email);

        List<PostDto> postDtos = new ArrayList<>();

        List<Post> posts = postRepository.findByUserId(userId);
        if (checkUser == user) {
            for (Post post : posts) {
                PostDto dto = new PostDto();
                BeanUtils.copyProperties(post, dto);
                postDtos.add(dto);
            }
        }
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByTagName(String tagName) {
        List<PostDto> postDtoList = new ArrayList<>();
        List<Post> posts = postRepository.findAllByTagName(tagName);
        for (Post post:posts) {
            PostDto postDto = new PostDto();
            BeanUtils.copyProperties(post,postDto);
            postDtoList.add(postDto);
        }
        return postDtoList;
    }
}
