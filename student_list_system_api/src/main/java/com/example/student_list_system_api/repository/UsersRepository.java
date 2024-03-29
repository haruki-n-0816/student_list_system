package com.example.student_list_system_api.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.student_list_system_api.domain.Users;

@Repository
public class UsersRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAll() {

        String query = "select * from users;";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(query);

        return users;
    }

    public List<Map<String, Object>> usersGet(Integer page) {

        // 定数管理する
        int showPeople = 10;
        String query = "select * from users limit ?, ?;";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(query, page * showPeople, showPeople);

        return users;
    }

    public List<Map<String, Object>> usersCountGet() {

        String query = "select count(*) as 'count' from users;";
        List<Map<String, Object>> count = jdbcTemplate.queryForList(query);

        return count;
    }

    public boolean createUserRecord(Users users) {
        
        String query = "insert into users(name, mail_address, profile_image_path) values(?, ?, ?);";
        jdbcTemplate.update(query, users.getName(), users.getMailAddress(), users.getProfileImagePath());

        return true;
    }

    public List<Map<String, Object>> tableStatusGet() {

        String query = "show table status;";
        List<Map<String, Object>> count = jdbcTemplate.queryForList(query);

        return count;
    }

    public boolean deleteUserRecord(Integer id) {

        String query = "delete from users where id = ?;";
        jdbcTemplate.update(query, id);

        return true;
    }

    public List<Map<String, Object>> deleteUserImage(Integer id) {

        String query = "select profile_image_path from users where id = ?;";

        return jdbcTemplate.queryForList(query, id);
    }

    public boolean updateUserRecord(Users users) {

        String query = "update users set name = ?, mail_address = ?, profile_image_path = ? where id = ?;";
        jdbcTemplate.update(query, users.getName(), users.getMailAddress(), users.getProfileImagePath(), users.getId());

        return true;
    }
}
