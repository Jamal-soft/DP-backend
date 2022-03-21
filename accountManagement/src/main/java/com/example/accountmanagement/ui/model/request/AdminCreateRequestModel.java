package com.example.accountmanagement.ui.model.request;

import lombok.Data;

@Data
public class AdminCreateRequestModel {
    private String name;
    private String email;
    private String password;
    private String image;
}
