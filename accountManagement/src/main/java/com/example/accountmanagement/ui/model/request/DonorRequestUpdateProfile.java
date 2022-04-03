package com.example.accountmanagement.ui.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DonorRequestUpdateProfile {
    private String name;
    private MultipartFile image;
    private String location;
    private String phoneNumber;
    private String description;
}
