package com.example.accountmanagement.service;

import com.example.accountmanagement.shared.OrganisationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface OrganisationService extends UserDetailsService {
    OrganisationDto createOrganisation(OrganisationDto organisationDto);
    OrganisationDto getOrganisation(String email);
   /* OrganisationDto getUserByUserId(String userId);

    OrganisationDto updateUser(String id,OrganisationDto userDto);

    void deleteUser(String userId);

    List<OrganisationDto> getUsers(int page, int limit);

    boolean verifyEmailToken(String token);

    boolean requestPasswordReset(String email);

    boolean resetPassword(String token, String password);*/

}
