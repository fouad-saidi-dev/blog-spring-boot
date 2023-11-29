package com.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDto {
    private Long Id;
    private String fileID;
    private String name;
    private String url;
}
