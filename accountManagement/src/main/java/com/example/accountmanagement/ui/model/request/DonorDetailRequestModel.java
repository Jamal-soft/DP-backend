package com.example.accountmanagement.ui.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DonorDetailRequestModel {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private MultipartFile image;
}
