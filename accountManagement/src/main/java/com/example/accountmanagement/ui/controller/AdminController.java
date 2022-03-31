package com.example.accountmanagement.ui.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.accountmanagement.exceptions.OrganisationServiceException;
import com.example.accountmanagement.io.entity.AdminEntity;
import com.example.accountmanagement.io.repository.AdminRepository;
import com.example.accountmanagement.ui.model.request.AdminCreateRequestModel;
import com.example.accountmanagement.ui.model.response.AdminResponseCreation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Cloudinary cloudinaryConfig;

    public AdminController(Cloudinary cloudinaryConfig) {
        this.cloudinaryConfig = cloudinaryConfig;
    }


    @PostMapping("/admins")
    public AdminResponseCreation createAdmin(@ModelAttribute AdminCreateRequestModel adminCreateRequestModel) throws Exception {
        if (adminRepository.findByEmail(adminCreateRequestModel.getEmail()) != null) throw new Exception("user already exists");
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setEmail(adminCreateRequestModel.getEmail());
        adminEntity.setName(adminCreateRequestModel.getName());
        adminEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(adminCreateRequestModel.getPassword()));

        if (adminCreateRequestModel.getImage()!=null){


            try {
                MultipartFile image = adminCreateRequestModel.getImage();
                File uploadedFile = convertMultiPartToFile(image);
                Map uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
                boolean isDeleted = uploadedFile.delete();

                if (isDeleted){
                    System.out.println("File successfully deleted");
                }else
                    System.out.println("File doesn't exist");
                adminEntity.setImage(uploadResult.get("url").toString());
                //return  uploadResult.get("url").toString();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        AdminEntity adminEntity1= adminRepository.save(adminEntity);
        ModelMapper modelMapper = new ModelMapper();
        AdminResponseCreation creation = modelMapper.map(adminEntity1,AdminResponseCreation.class);
        return creation;


    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
