package com.blog.services.impl;

import com.blog.dto.FileDto;
import com.blog.entities.File;
import com.blog.repositories.FIleRepository;
import com.blog.services.FileService;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {

    private final Path root = Paths.get("uploads");

    @Autowired
    FIleRepository fIleRepository;


    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload");
        }
    }

    @Override
    public void saveFile(MultipartFile file) {

        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));

        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists");
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {

        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return  resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch(MalformedURLException e) {
            throw new RuntimeException("Error: "+e.getMessage());
        }
    }

    @Override
    public File store(MultipartFile file) throws IOException {
        // for store in uploads folder
        //Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String url = String.valueOf(this.root.resolve(file.getOriginalFilename()));
        File fileEntity = new File(null,"12345",fileName,url);
        return fIleRepository.save(fileEntity);
    }
}
