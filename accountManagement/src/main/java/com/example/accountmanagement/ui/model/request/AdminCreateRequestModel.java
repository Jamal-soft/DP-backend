package com.example.accountmanagement.ui.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AdminCreateRequestModel {
    private String name;
    private String email;
    private String password;
    private MultipartFile image;
}
