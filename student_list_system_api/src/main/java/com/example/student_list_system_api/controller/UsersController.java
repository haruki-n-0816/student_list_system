package com.example.student_list_system_api.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
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
            @RequestParam("userImageConfirm") MultipartFile userImageConfirm, Model model) throws IOException {

        // 形式変換
        byte[] profileImageEncode = userImageConfirm.getBytes();
        
        // 次のid取得
        BigInteger id = service.autoIncrementCountGet();
        
        // 保存ファイル名決定
        String IdTimeFilename = id + "_" + DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now()) + userImageConfirm.getContentType();
                
        System.out.println("保存先:" + IdTimeFilename);
        // ファイル保存先決定
        String filePath = "student_list_system_front/public/student_list_system_profileImage/" + IdTimeFilename;

        // db用保存先
        String filePathDb = "public/student_list_system_profileImage/" + IdTimeFilename;
        
        // 保存処理
        Files.write(Paths.get(filePath), profileImageEncode);
        
        // db登録処理
        service.createUserPost(userNameConfirm, userMailAddressConfirm, filePathDb);

    }

    @GetMapping("/delete_complete")
    public void deleteCompleteUserApi(Integer id) {
        System.out.println("削除対象id:" + id);
        service.deleteUserPost(id);

    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Integer id, @RequestParam("userName") String name,
            @RequestParam("mailAddress") String mailAddress, @RequestParam("profileImage") String profileImageString,Model model) {

        model.addAttribute("id", id);
        model.addAttribute("currentName", name);
        model.addAttribute("currentMailAddress", mailAddress);
        model.addAttribute("profileImagePath",profileImageString);

        return "users/update";
    }

    @PostMapping("/update_confirm")
    public String updateConfirmUser(@RequestParam("id") Integer id, @RequestParam("userName") String name,
            @RequestParam("mailAddress") String mailAddress,
            @RequestPart("profileImage") MultipartFile profileImage, Model model) throws IOException {

        String base64 = Base64.getEncoder().encodeToString(profileImage.getBytes());

        model.addAttribute("confirmId", id);
        model.addAttribute("confirmName", name);
        model.addAttribute("confirmMailAddress", mailAddress);
        model.addAttribute("confirmProfileImage", base64);

        return "users/update-confirm";
    }

    @PostMapping("/update_complete")
    public String updateCompleteUser(@RequestParam("id") Integer id, @RequestParam("userName") String name,
            @RequestParam("mailAddress") String mailAddress,
            @RequestParam("profileImage") String profileImageString, Model model) throws IOException {

        byte[] profileImageDecoded = Base64.getDecoder().decode(profileImageString);

        String IdTimeFilename = id + "_" + DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now())
                + ".png";

        String filePath = "student_list_system_front/public/student_list_system_profileImage"
                + IdTimeFilename;
        String filePathDb = "/student_list_system_profileImage/" + IdTimeFilename;

        Files.write(Paths.get(filePath), profileImageDecoded);
        
        service.updateUserPost(id, name, mailAddress,filePathDb);

        return "redirect:/users";
    }
}
