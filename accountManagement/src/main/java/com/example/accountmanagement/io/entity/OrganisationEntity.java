package com.example.accountmanagement.io.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;




@Entity
@Table(name = "organisation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String encryptedPassword;
    private Long rib;
    private String category;
    private boolean verified;
    private byte[] verificationFile;
    private byte[] image;
    private String Location;
    private String description;

}
