package com.blog.requests;

import com.blog.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String title;
    private String description;
    private String body;
    private List<String> tags;
}
