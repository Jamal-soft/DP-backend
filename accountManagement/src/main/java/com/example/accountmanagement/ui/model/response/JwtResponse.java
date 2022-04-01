package com.example.accountmanagement.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponse {

        private String token;
        private Long id;
        private String email;
        private String name;
        private String role;
        private String image;

}
