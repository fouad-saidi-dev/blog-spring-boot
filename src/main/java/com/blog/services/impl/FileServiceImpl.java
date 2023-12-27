package com.blog.services.impl;

import com.blog.entities.File;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.repositories.FIleRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.responses.PostResponse;
import com.blog.services.FileService;
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
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService {

    private final Path root = Paths.get("uploads");

    @Autowired
    FIleRepository fIleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload");
        }
    }

    @Override
    public void saveFile(MultipartFile file,String email) {

        User checkUser = userRepository.findByEmail(email);

        try {
            String fileName = checkUser.getFirstName() + "-" +checkUser.getLastName()+getFileExtension(file);

            int i=1;
            while (Files.exists(this.root.resolve(fileName))){
                fileName = checkUser.getFirstName() + "-" +checkUser.getLastName()+i+getFileExtension(file);
                i++;
            }

            Files.copy(file.getInputStream(), this.root.resolve(fileName));
        } catch (Exception e) {

            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists");
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void savePicture(MultipartFile file,String email,String postId) {

        Post checkPost = postRepository.findByPostId(postId);

        try {
            String fileName = checkPost.getPostId()+getFileExtension(file);

            Files.copy(file.getInputStream(), this.root.resolve(fileName));
        } catch (Exception e) {

            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists");
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    private String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        return "";
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
        Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String url = String.valueOf(this.root.resolve(file.getOriginalFilename()));
        File fileEntity = new File(null,"12345",fileName,url);
        return fIleRepository.save(fileEntity);
    }
    @Override
    public Stream<File> getAllFiles() {
        return fIleRepository.findAll().stream();
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path file = root.resolve(fileName);
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


}
