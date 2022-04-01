package com.example.accountmanagement.ui.controller;

import com.example.accountmanagement.exceptions.DonorServiceException;
import com.example.accountmanagement.io.entity.DonorEntity;
import com.example.accountmanagement.service.DonorService;
import com.example.accountmanagement.shared.DonorDto;
import com.example.accountmanagement.ui.model.request.DonorDetailRequestModel;
import com.example.accountmanagement.ui.model.request.DonorRequestUpdateProfile;
import com.example.accountmanagement.ui.model.request.ResetPasswordRequestModel;
import com.example.accountmanagement.ui.model.response.DonorResp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonorController {
    @Autowired
    DonorService donorService;


    @PostMapping(path = "/signup/donors")
    public DonorResp createDonor(@ModelAttribute DonorDetailRequestModel donorDetailRequestModel) throws Exception {
        if (donorDetailRequestModel.getEmail().isEmpty()) throw new DonorServiceException("missing required field");
        ModelMapper modelMapper=new ModelMapper();
        DonorEntity createdDonor = donorService.createDonor(donorDetailRequestModel);
        DonorResp returnValue = modelMapper.map(createdDonor, DonorResp.class);

        return returnValue;
    }
    @PostMapping(path = "/donors/reset-password")
    public String resetPassword(@RequestBody ResetPasswordRequestModel resetPasswordRequestModel) throws Exception {
        if (resetPasswordRequestModel.newPass.isEmpty() && resetPasswordRequestModel.currentPass.isEmpty()) throw new DonorServiceException("missing required field");
        return donorService.resetPassword(resetPasswordRequestModel);
    }

    @PostMapping(path = "/donors/update-profile/")
    public String updateProfile(@RequestBody DonorRequestUpdateProfile DonorRequestUpdateProfile) throws Exception {
        return donorService.updateProfile(DonorRequestUpdateProfile);
    }
}
