package com.example.accountmanagement.service;

import com.example.accountmanagement.io.entity.OrganisationEntity;
import com.example.accountmanagement.shared.OrganisationDto;
import com.example.accountmanagement.ui.model.request.OrganisationDetailsRequestModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface OrganisationService extends UserDetailsService {


    OrganisationEntity createOrganisation(OrganisationDetailsRequestModel organisationDetailsRequestModel);

    OrganisationDto getOrganisation(String email);


}
