package com.example.accountmanagement.service;


import com.cloudinary.Cloudinary;
import com.example.accountmanagement.exceptions.OrganisationServiceException;
import com.example.accountmanagement.io.entity.OrganisationEntity;
import com.example.accountmanagement.io.repository.OrganisationRepository;
import com.example.accountmanagement.shared.OrganisationDto;
import com.example.accountmanagement.ui.model.request.OrganisationDetailsRequestModel;
import com.example.accountmanagement.ui.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganisationServiceImpl implements OrganisationService{
    private Utils utils;
    @Autowired
    OrganisationRepository organisationRepository;


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public OrganisationServiceImpl( Utils utils) {
        this.utils = utils;
    }


    @Override
    public OrganisationEntity createOrganisation(OrganisationDetailsRequestModel organisationDetailsRequestModel) {
        if (organisationRepository.findByEmail(organisationDetailsRequestModel.getEmail()) != null) throw new OrganisationServiceException("user already exists");
        ModelMapper modelMapper = new ModelMapper();


        OrganisationEntity organisationEntity = modelMapper.map(organisationDetailsRequestModel, OrganisationEntity.class);


        organisationEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(organisationDetailsRequestModel.getPassword()));
        MultipartFile image = organisationDetailsRequestModel.getImage();

        if (image!=null){
            String path = utils.uploadFile(image);
            if (path!=null){
                organisationEntity.setImage(path);
            }
        }
        MultipartFile file = organisationDetailsRequestModel.getVerificationFile();
        if (file!=null){
            String path = utils.uploadFile(file);
            if (path!=null){
                organisationEntity.setVerificationFile(path);
            }
        }
        OrganisationEntity storedUserDetails = organisationRepository.save(organisationEntity);
        // Send an email message to user to verify their email address



        return storedUserDetails;
    }

    @Override
    public OrganisationDto getOrganisation(String email) {
        OrganisationEntity userEntity = organisationRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        OrganisationDto returnValue = new OrganisationDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }



    public List<OrganisationDto> getOrganisations(int page, int limit) {
        List<OrganisationDto> returnValue = new ArrayList<>();
        if (page > 0) page = page - 1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<OrganisationEntity> usersPage = organisationRepository.findAll(pageableRequest);
        List<OrganisationEntity> organisations = usersPage.getContent();

        for (OrganisationEntity organisationEntity : organisations) {
            OrganisationDto userDto = new OrganisationDto();
            BeanUtils.copyProperties(organisationEntity, userDto);
            returnValue.add(userDto);
        }


        return returnValue;

    }



    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        OrganisationEntity organisationEntity = organisationRepository.findByEmail(email);
        if (organisationEntity == null) throw new UsernameNotFoundException(email);

        return new User(organisationEntity.getEmail(),organisationEntity.getEncryptedPassword(),
        true, true,
        true, true, new ArrayList<>());


    }



}
