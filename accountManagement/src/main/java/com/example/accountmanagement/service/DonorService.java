package com.example.accountmanagement.service;

import com.example.accountmanagement.exceptions.OrganisationServiceException;
import com.example.accountmanagement.io.entity.DonorEntity;
import com.example.accountmanagement.io.repository.DonorRepository;
import com.example.accountmanagement.ui.model.request.DonorDetailRequestModel;
import com.example.accountmanagement.ui.model.request.DonorRequestUpdateProfile;
import com.example.accountmanagement.ui.model.request.ResetPasswordRequestModel;
import com.example.accountmanagement.ui.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class DonorService {
    @Autowired
    DonorRepository donorRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    private Utils utils;

    public DonorService(Utils utils) {
        this.utils = utils;
    }

    public DonorEntity createDonor(DonorDetailRequestModel donorDetailRequestModel) {
        if (donorRepository.findByEmail(donorDetailRequestModel.getEmail()) != null) throw new OrganisationServiceException("user already exists");
        DonorEntity donorEntity =new DonorEntity();
        donorEntity.setName(donorDetailRequestModel.getName());
        donorEntity.setPhoneNumber(donorDetailRequestModel.getPhoneNumber());
        donorEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(donorDetailRequestModel.getPassword()));
        donorEntity.setEmail(donorDetailRequestModel.getEmail());
        MultipartFile image = donorDetailRequestModel.getImage();


        if (image!=null){

            String path = utils.uploadFile(image);
            if (path!=null){
                donorEntity.setImage(path);
            }

        }

        return donorRepository.save(donorEntity);
        // Send an email message to user to verify their email address



    }


    public String resetPassword(ResetPasswordRequestModel resetPasswordRequestModel) {
        if (!resetPasswordRequestModel.newPass.equals(resetPasswordRequestModel.newPassAgain)) return "doesn't match ";
        DonorEntity donorEntity = donorRepository.findById(resetPasswordRequestModel.donorId).get();
        if (donorEntity!=null){
            if (!bCryptPasswordEncoder.matches(resetPasswordRequestModel.currentPass,donorEntity.getEncryptedPassword())) return "current password is false";
            donorEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(resetPasswordRequestModel.newPass));
            donorRepository.save(donorEntity);
            return "password changed succesfully";
        }
        return "can't find the donor";
    }

    public String updateProfile(DonorRequestUpdateProfile donorRequestUpdateProfile) {
        DonorEntity donorEntity = donorRepository.findById(donorRequestUpdateProfile.getId()).get();
        if (donorEntity!=null){
            donorEntity.setLocation(donorRequestUpdateProfile.getLocation());
            donorEntity.setName(donorRequestUpdateProfile.getName());
            donorEntity.setPhoneNumber(donorRequestUpdateProfile.getPhoneNumber());
            donorRepository.save(donorEntity);
            return "profile updated succesfuly";
        }
        return "donor does not exist";
    }
}
