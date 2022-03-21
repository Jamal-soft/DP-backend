package com.example.accountmanagement.service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.accountmanagement.exceptions.OrganisationServiceException;
import com.example.accountmanagement.io.entity.OrganisationEntity;
import com.example.accountmanagement.io.repository.OrganisationRepository;
import com.example.accountmanagement.shared.OrganisationDto;
import com.example.accountmanagement.ui.model.request.OrganisationDetailsRequestModel;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrganisationServiceImpl implements OrganisationService{
    @Autowired
    OrganisationRepository organisationRepository;
    private final Cloudinary cloudinaryConfig;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public OrganisationServiceImpl(Cloudinary cloudinaryConfig) {
        this.cloudinaryConfig = cloudinaryConfig;
    }


    @Override
    public OrganisationEntity createOrganisation(OrganisationDetailsRequestModel organisationDetailsRequestModel) {
        if (organisationRepository.findByEmail(organisationDetailsRequestModel.getEmail()) != null) throw new OrganisationServiceException("user already exists");
        ModelMapper modelMapper = new ModelMapper();


        OrganisationEntity organisationEntity = modelMapper.map(organisationDetailsRequestModel, OrganisationEntity.class);


        organisationEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(organisationDetailsRequestModel.getPassword()));
        if (organisationDetailsRequestModel.getImage()!=null){


        try {
            MultipartFile image = organisationDetailsRequestModel.getImage();
            File uploadedFile = convertMultiPartToFile(image);
            Map uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
            boolean isDeleted = uploadedFile.delete();

            if (isDeleted){
                System.out.println("File successfully deleted");
            }else
                System.out.println("File doesn't exist");
                organisationEntity.setImage(uploadResult.get("url").toString());
            //return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
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

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
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

/*
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
*/
    }

/*
    @Override
    public boolean verifyEmailToken(String token) {
        boolean returnValue = false;

        // Find user by token
        UserEntity userEntity = organisationRepository.findUserByEmailVerificationToken(token);

        if (userEntity != null) {
            boolean hastokenExpired = Utils.hasTokenExpired(token);
            if (!hastokenExpired) {
                userEntity.setEmailVerificationToken(null);
                userEntity.setEmailVerificationStatus(Boolean.TRUE);
                organisationRepository.save(userEntity);
                returnValue = true;
            }
        }
        return returnValue;
    }

    @Override
    public boolean requestPasswordReset(String email) {
        boolean returnValue = false;

        UserEntity userEntity = organisationRepository.findByEmail(email);

        if (userEntity == null) {
            return returnValue;
        }

        String token = new Utils().generatePasswordResetToken(userEntity.getUserId());

        PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
        passwordResetTokenEntity.setToken(token);
        passwordResetTokenEntity.setUserDetails(userEntity);
        passwordResetTokenRepository.save(passwordResetTokenEntity);

        returnValue = new AmazonSES().sendPasswordResetRequest(
                userEntity.getFirstName(),
                userEntity.getEmail(),
                token);

        return returnValue;
    }

    @Override
    public boolean resetPassword(String token, String password) {
        boolean returnValue = false;

        if( Utils.hasTokenExpired(token) )
        {
            return returnValue;
        }

        PasswordResetTokenEntity passwordResetTokenEntity = passwordResetTokenRepository.findByToken(token);

        if (passwordResetTokenEntity == null) {
            return returnValue;
        }

        // Prepare new password
        String encodedPassword = bCryptPasswordEncoder.encode(password);

        // Update User password in database
        UserEntity userEntity = passwordResetTokenEntity.getUserDetails();
        userEntity.setEncryptedPassword(encodedPassword);
        UserEntity savedUserEntity = organisationRepository.save(userEntity);

        // Verify if password was saved successfully
        if (savedUserEntity != null && savedUserEntity.getEncryptedPassword().equalsIgnoreCase(encodedPassword)) {
            returnValue = true;
        }

        // Remove Password Reset token from database
        passwordResetTokenRepository.delete(passwordResetTokenEntity);

        return returnValue;
    }*/
}
