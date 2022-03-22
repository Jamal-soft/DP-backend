package com.example.accountmanagement.ui.controller;


import com.example.accountmanagement.exceptions.OrganisationServiceException;
import com.example.accountmanagement.io.entity.OrganisationEntity;
import com.example.accountmanagement.service.OrganisationServiceImpl;
import com.example.accountmanagement.shared.OrganisationDto;
import com.example.accountmanagement.ui.model.request.OrganisationDetailsRequestModel;
import com.example.accountmanagement.ui.model.response.OrganisationResp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController

public class OrganisationController {
    @Autowired
    OrganisationServiceImpl organisationService;



    @GetMapping("/organisations")
    public List<OrganisationResp> getOrganisations(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "limit", defaultValue = "25") int limit){
        List<OrganisationDto> organisations= organisationService.getOrganisations(page,limit);
        List<OrganisationResp> returnValue = new ArrayList<>();

        for (OrganisationDto organisationDto: organisations){
            OrganisationResp organisationResp= new OrganisationResp();
            BeanUtils.copyProperties(organisationDto,organisationResp);
            returnValue.add(organisationResp);
        }



        return returnValue;

    }


    @PostMapping("/signup/organisations")
    public OrganisationResp createOrganisation(@RequestBody OrganisationDetailsRequestModel organisationDetailsRequestModel) throws Exception{
        if (organisationDetailsRequestModel.getEmail().isEmpty()) throw new OrganisationServiceException("missing required field");
        ModelMapper modelMapper = new ModelMapper();
        OrganisationEntity createdOrganisation= organisationService.createOrganisation(organisationDetailsRequestModel);
        OrganisationResp returnValue = modelMapper.map(createdOrganisation,OrganisationResp.class);
        //BeanUtils.copyProperties(createdUser,returnValue);

        return returnValue;

    }


}
