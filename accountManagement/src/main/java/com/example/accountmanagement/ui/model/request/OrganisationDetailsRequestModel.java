package com.example.accountmanagement.ui.model.request;

import lombok.Data;

@Data
public class OrganisationDetailsRequestModel {
    private String name;
    private String email;
    private String password;
    private Long rib;
    private String category;
    private boolean verified;
    private byte[] verificationFile;
    private byte[] image;
    private String Location;
    private String description;
}
