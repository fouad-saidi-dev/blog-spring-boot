package com.blog.requests;

import com.blog.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class PostRequest {
    private String title;
    private String description;
    private String body;
}
