package com.example.student_list_system_api.service;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student_list_system_api.domain.Users;
import com.example.student_list_system_api.repository.UsersRepository;

@Service
public class UsersService {
    
    @Autowired
    private UsersRepository repository;

    public List<Users> getUsers(){

        List<Map<String, Object>> querySet = repository.findAll();
        List<Users> users = new ArrayList<>();
        for (var record : querySet) {
            var user = new Users();
            user.setId((Integer)(record.get("id")));
            user.setName((String)record.get("name"));
            user.setMailAddress((String)record.get("mail_address"));
            user.setProfileImagePath((String)record.get("profile_image_path"));
            users.add(user);
        }
        
        return users;
    }

    public long countGet(){

        List<Map<String, Object>> querySet = repository.usersCountGet();
        long count = 0;
        for(var record : querySet){
            count = (Long)record.get("count");
        }

        long maxPage = (count / 10);
        if (count % 10 > 0) {
            maxPage++;
        }

        return maxPage;
    }

    public BigInteger autoIncrementCountGet(){
        List<Map<String, Object>> TableStatsu = repository.tableStatusGet();

        BigInteger autoIncrement = BigInteger.ONE;
        for(var record : TableStatsu){
            autoIncrement = (BigInteger)record.get("Auto_increment");
        }
        return autoIncrement;
    }

    public boolean createUserPost(String name, String mail,String path){
        
        Users user = new Users();
        user.setName(name);
        user.setMailAddress(mail);
        user.setProfileImagePath(path);
        
        repository.createUserRecord(user);

        return true;
    }
    
    public boolean deleteUserPost(Integer id){
        List<Map<String, Object>> pathRecord = repository.deleteUserImage(id);
        String path = "";
        for(var record : pathRecord){
            path = (String)record.get("profile_image_path");
        }
        
        File file = new File("../student_list_system_front/public/student_list_system_profileImage/" + path);

        if (file.delete()) {
            System.out.println(file.getName() + " を削除しました。");
        } else {
            System.out.println("画像ファイルを削除できませんでした。");
        }

        repository.deleteUserRecord(id);

        return true;
    }

    public boolean updateUserPost(Integer id, String name, String mailAddress, String path){

        Users user = new Users();
        user.setId(id);
        user.setName(name);
        user.setMailAddress(mailAddress);
        user.setProfileImagePath(path);
        repository.updateUserRecord(user);

        return true;
    }
}
