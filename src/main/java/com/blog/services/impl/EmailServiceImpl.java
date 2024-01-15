package com.blog.services.impl;

import com.blog.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(String to, String user) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        try {
            messageHelper.setTo(to);
            messageHelper.setSubject("Welcome to Our IT Blog");
            messageHelper.setText(emailContent(user),true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String emailContent(String user) {
        try {
            ClassPathResource resource = new ClassPathResource("email.html");
            byte[] contentBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String templateContent = new String(contentBytes, StandardCharsets.UTF_8);

            return templateContent.replace("[User]", user);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception accordingly (e.g., log the error or throw a custom exception)
            return "";
        }
    }
}
