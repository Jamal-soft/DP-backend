package com.example.accountmanagement.shared;

import lombok.Data;

@Data
public class DonorDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String image;
}
