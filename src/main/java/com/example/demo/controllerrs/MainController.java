package com.example.demo.controllerrs;

import com.example.demo.models.User;
import com.example.demo.services.EmailService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
     private EmailService emailService;

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/upl")
    public String upl() {
        return "images";
    }


    @PostMapping("/upl")
    public @ResponseBody
    String save(@RequestParam String name,
                @RequestParam String surname,
                @RequestParam String email,
                @RequestParam MultipartFile img) throws IOException, MessagingException {
        final User user = new User(name, surname, email, img.getOriginalFilename());
        userService.transferFile(img);
        userService.save(user);

        emailService.send(email,img);
        return "!!!";
    }
}


