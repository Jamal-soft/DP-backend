package com.example.accountmanagement.ui.controller;

import com.example.accountmanagement.exceptions.DonorServiceException;
import com.example.accountmanagement.service.DonorService;
import com.example.accountmanagement.shared.DonorDto;
import com.example.accountmanagement.ui.model.request.DonorDetailRequestModel;
import com.example.accountmanagement.ui.model.response.DonorResp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonorController {
    @Autowired
    DonorService donorService;


    @PostMapping(path = "/signup/donors")
    public DonorResp createDonor(@RequestBody DonorDetailRequestModel donorDetailRequestModel) throws Exception {
        if (donorDetailRequestModel.getEmail().isEmpty()) throw new DonorServiceException("missing required field");
        ModelMapper modelMapper = new ModelMapper();
        DonorDto donorDto = modelMapper.map(donorDetailRequestModel, DonorDto.class);
        DonorDto createdDonor = donorService.createDonor(donorDto);
        DonorResp returnValue = modelMapper.map(createdDonor, DonorResp.class);

        return returnValue;
    }
}
