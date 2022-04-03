package com.example.accountmanagement.ui.controller;

import com.example.accountmanagement.io.entity.AdminEntity;
import com.example.accountmanagement.io.entity.DonorEntity;
import com.example.accountmanagement.io.entity.OrganisationEntity;
import com.example.accountmanagement.io.repository.AdminRepository;
import com.example.accountmanagement.io.repository.DonorRepository;
import com.example.accountmanagement.io.repository.OrganisationRepository;
import com.example.accountmanagement.security.SecurityConstants;
import com.example.accountmanagement.service.OrganisationService;
import com.example.accountmanagement.ui.model.request.LoginRequestModel;
import com.example.accountmanagement.ui.model.request.OrganisationLoginRequestModel;
import com.example.accountmanagement.ui.model.response.JwtResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class AuthenticationController {
    @Autowired
    OrganisationRepository organisationRepository;
    @Autowired
    DonorRepository donorRepository;
    @Autowired
    AdminRepository adminRepository;


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(path = "/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestModel loginRequestModel){
        DonorEntity donorEntity = donorRepository.findByEmail(loginRequestModel.getEmail());
        OrganisationEntity organisationEntity = organisationRepository.findByEmail(loginRequestModel.getEmail());
        AdminEntity adminEntity = adminRepository.findByEmail(loginRequestModel.getEmail());

        if ( organisationEntity!=null && organisationEntity.isVerified()){
            if ( bCryptPasswordEncoder.matches(loginRequestModel.getPassword(), organisationEntity.getEncryptedPassword())) {
                String token = generateToken(loginRequestModel);

                return ResponseEntity.ok(new JwtResponse(token,
                        organisationEntity.getId(),
                        organisationEntity.getEmail(),
                        organisationEntity.getName(),
                        organisationEntity.getRole(),
                        organisationEntity.getImage()));
            }
        }

        if ( donorEntity!=null){
            if ( bCryptPasswordEncoder.matches(loginRequestModel.getPassword(), donorEntity.getEncryptedPassword())) {
                String token = generateToken(loginRequestModel);

                return ResponseEntity.ok(new JwtResponse(token,
                        donorEntity.getId(),
                        donorEntity.getEmail(),
                        donorEntity.getName(),
                        donorEntity.getRole(),
                        donorEntity.getImage()));
            }
        }

        if ( adminEntity!=null){
            if ( bCryptPasswordEncoder.matches(loginRequestModel.getPassword(), adminEntity.getEncryptedPassword())) {
                String token = generateToken(loginRequestModel);

                return ResponseEntity.ok(new JwtResponse(token,
                        adminEntity.getId(),
                        adminEntity.getEmail(),
                        adminEntity.getName(),
                        adminEntity.getRole(),
                        adminEntity.getImage()));
            }
        }

        return  ResponseEntity.badRequest()
                .body("not found");


    }

    private String generateToken(LoginRequestModel loginRequestModel){
        String token = Jwts.builder()
                .setSubject(loginRequestModel.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                .compact();
        token = SecurityConstants.TOKEN_PREFIX + token;
        return token;
    }
}
