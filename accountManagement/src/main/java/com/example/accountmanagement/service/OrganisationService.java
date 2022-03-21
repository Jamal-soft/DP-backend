package com.example.accountmanagement.service;

import com.example.accountmanagement.io.entity.OrganisationEntity;
import com.example.accountmanagement.shared.OrganisationDto;
import com.example.accountmanagement.ui.model.request.OrganisationDetailsRequestModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface OrganisationService extends UserDetailsService {


    OrganisationEntity createOrganisation(OrganisationDetailsRequestModel organisationDetailsRequestModel);

    OrganisationDto getOrganisation(String email);
   /* OrganisationDto getUserByUserId(String userId);

    OrganisationDto updateUser(String id,OrganisationDto userDto);

    void deleteUser(String userId);

    List<OrganisationDto> getUsers(int page, int limit);

    boolean verifyEmailToken(String token);

    boolean requestPasswordReset(String email);

    boolean resetPassword(String token, String password);*/

}
