package com.blog.services;

import com.blog.entities.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileService {

     void init();
     void saveFile(MultipartFile file);
     Resource load(String filename);
     File store(MultipartFile file) throws IOException;
     Stream<File> getAllFiles();
}
