package com.example.accountmanagement.ui.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class OrganisationDetailsRequestModel {
    private String name;
    private String email;
    private String password;
    private Long rib;
    private String category;
    private boolean verified;
    private String verificationFile;
    private MultipartFile image;
    private String Location;
    private String description;
    private String phoneNumber;

}
