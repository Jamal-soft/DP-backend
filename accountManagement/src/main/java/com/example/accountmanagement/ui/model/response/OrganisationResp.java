package com.example.accountmanagement.ui.model.response;

import lombok.Data;

@Data
public class OrganisationResp {
    private Long id;
    private String name;
    private String email;
    private Long rib;
    private String category;
    private boolean verified;
    private String  image;
    private String Location;
    private String description;
    private String phoneNumber;

}
