package com.example.accountmanagement.io.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String role = "ADMIN";
    private String encryptedPassword;
    private String image;
}
