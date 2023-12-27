package com.blog.controllers;

import com.blog.entities.File;
import com.blog.responses.FileResponse;
import com.blog.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.service.ResponseMessage;

import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file")MultipartFile file) {

        String message = "";
        try {
            fileService.store(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new FileResponse(message));
        }catch(Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileResponse(message));
        }
    }

    @GetMapping("/get/{filename}")
    public ResponseEntity<byte[]> serveFile(@PathVariable String filename) {

        Resource file = fileService.loadAsResource(filename);

        try {
            byte[] data = Files.readAllBytes(file.getFile().toPath());

            MediaType mediaType = MediaTypeFactory.getMediaType(file).orElse(MediaType.APPLICATION_OCTET_STREAM);

            // url
            String url = MvcUriComponentsBuilder.fromMethodName(FileController.class,"getFile",file.getFilename().toString()).build().toString();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType);
            headers.add(url,"url");


            return new ResponseEntity<>(data, headers,HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException("Error loading the file: " + filename, e);
        }
    }

    @GetMapping("/test/{filename}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        org.springframework.core.io.Resource file = fileService.load(filename);
        return ResponseEntity.ok().body(file);
    }

    @GetMapping("")
    public ResponseEntity<List<FileResponse>> getListFiles() {
        List<FileResponse> files = fileService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getUrl())
                    .toUriString();

            return new FileResponse(fileDownloadUri);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @PostMapping("/upload/avatar")
    public ResponseEntity<FileResponse> uploadFiles(@RequestParam("files") MultipartFile[] files, Principal principal) {
        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file -> {
                fileService.saveFile(file, principal.getName());
                fileNames.add(file.getOriginalFilename());
            });

            message = "Uploaded the files successfully: " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new FileResponse(message));
        } catch (Exception e) {
            message = "Fail to upload files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileResponse(message));
        }
    }

    @PostMapping(path = "/upload/post/{postId}")
    public ResponseEntity<FileResponse> uploadPicture(@RequestParam("files") MultipartFile[] files, Principal principal,@PathVariable String postId) {
        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file -> {
                fileService.savePicture(file, principal.getName(),postId);
                fileNames.add(file.getOriginalFilename());
            });

            message = "Uploaded the files successfully: " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new FileResponse(message));
        } catch (Exception e) {
            message = "Fail to upload files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileResponse(message));
        }
    }

}
