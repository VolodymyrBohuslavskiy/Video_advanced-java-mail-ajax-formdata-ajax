package com.example.demo.services;


import com.example.demo.DAO.UserDAO;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public void save(User user) {
        userDAO.save(user);
    }

    public List<User> findAll() {
        final List<User> all = userDAO.findAll(Sort.by("id"));
        Collections.reverse(all);
        return all;
    }

    public void transferFile(MultipartFile img) throws IOException {
        String path = "E:\\IdeaProjects\\Advanced_Java\\Video_advanced-java-mail-ajax-formdata-ajax\\src\\main\\resources\\static";
        img.transferTo(new File(path, img.getOriginalFilename()));

    }

}
