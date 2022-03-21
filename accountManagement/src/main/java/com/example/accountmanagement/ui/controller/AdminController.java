package com.example.accountmanagement.ui.controller;

import com.example.accountmanagement.io.entity.AdminEntity;
import com.example.accountmanagement.io.repository.AdminRepository;
import com.example.accountmanagement.ui.model.request.AdminCreateRequestModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    AdminRepository adminRepository;


    @PostMapping("/admins")
    public AdminEntity createAdmin(AdminCreateRequestModel adminCreateRequestModel){
        ModelMapper modelMapper = new ModelMapper();
        AdminEntity adminEntity = modelMapper.map(adminCreateRequestModel,AdminEntity.class);
        return adminRepository.save(adminEntity);

    }
}
