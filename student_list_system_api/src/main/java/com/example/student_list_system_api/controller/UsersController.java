package com.example.student_list_system_api.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.student_list_system_api.domain.Users;
import com.example.student_list_system_api.service.UsersService;


@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:8080")
public class UsersController {

    @Autowired
    UsersService service;

    @GetMapping("/users")
    public List<Users> getUser() {
        List<Users> users = service.getUsers();

        return users;
    }

    @PostMapping("/create_complete")
    public void createCompleteUser(@RequestParam("userNameConfirm") String userNameConfirm,
    @RequestParam("userMailAddressConfirm") String userMailAddressConfirm, 
    @RequestParam("userImageConfirm") MultipartFile userImageConfirm) throws IOException {

        byte[] profileImageEncode = userImageConfirm.getBytes();

        BigInteger id = service.autoIncrementCountGet();
        
        String idTimeFilename = id + "_" + DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now()) + userImageConfirm.getContentType().replace("image/", ".");        
        String filePath = "../student_list_system_front/public/student_list_system_profileImage/" + idTimeFilename;
        String filePathDb = "student_list_system_profileImage/" + idTimeFilename;
            
        Files.write(Paths.get(filePath), profileImageEncode);
        service.createUserPost(userNameConfirm, userMailAddressConfirm, filePathDb);

    }

    @GetMapping("/delete_complete")
    public void deleteCompleteUserApi(Integer id) {
        System.out.println("削除対象id:" + id);
        service.deleteUserPost(id);

    }

    @PostMapping("/update_complete")
    public void updateCompleteUser(@RequestParam("userIdConfirm") Integer id,
            @RequestParam("userNameConfirm") String userNameConfirm,
            @RequestParam("userMailAddressConfirm") String userMailAddressConfirm,
            @RequestParam("userImageConfirm") MultipartFile userImageConfirm, Model model) throws IOException {

        byte[] profileImageEncode = userImageConfirm.getBytes();

        String idTimeFilename = id + "_" + DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now()) + userImageConfirm.getContentType().replace("image/", ".");        
        String filePath = "../student_list_system_front/public/student_list_system_profileImage/" + idTimeFilename;
        String filePathDb = "student_list_system_profileImage/" + idTimeFilename;

        Files.write(Paths.get(filePath), profileImageEncode);
        
        service.updateUserPost(id, userNameConfirm, userMailAddressConfirm,filePathDb);

    }
}
