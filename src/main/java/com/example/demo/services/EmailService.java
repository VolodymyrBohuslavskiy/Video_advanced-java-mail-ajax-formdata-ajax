package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@PropertySource("classpath:application.properties")
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    Environment env;

    public void send(String email, MultipartFile file) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        try {
            mimeMessage.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
            helper.setTo(email);
            helper.setText("Hello )", true);
            helper.addAttachment(file.getOriginalFilename(), file);
            helper.setSubject("KIT");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
