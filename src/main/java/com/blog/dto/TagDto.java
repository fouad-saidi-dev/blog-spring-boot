package com.blog.dto;

import com.blog.entities.Post;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
    private Long Id;
    private String tagId;
    @NotNull
    private String name;
    private List<Post> posts;
}
