package com.example.accountmanagement.ui.model.request;

import lombok.Data;

@Data
public class DonorRequestUpdateProfile {
    private Long id;
    private String name;
    private String image;
    private String location;
    private String phoneNumber;
    private String description;
}
