package com.blog.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeCommentResponse {
    private Boolean isLike;
    private String likeCommentId;
}
