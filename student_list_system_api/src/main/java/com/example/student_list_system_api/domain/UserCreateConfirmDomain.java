package com.example.student_list_system_api.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UserCreateConfirmDomain {
    private String userNameConfirm;
    private String userMailAddressConfirm;
    private MultipartFile userImageConfirm;
}
