package com.example.accountmanagement.service;


import com.example.accountmanagement.exceptions.OrganisationServiceException;
import com.example.accountmanagement.io.entity.OrganisationEntity;
import com.example.accountmanagement.io.repository.OrganisationRepository;
import com.example.accountmanagement.shared.OrganisationDto;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganisationServiceImpl implements OrganisationService{
    @Autowired
    OrganisationRepository organisationRepository;
/*    @Autowired
    Utils utils;*/

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public OrganisationDto createUser(OrganisationDto organisationDto) {
        if (organisationRepository.findByEmail(organisationDto.getEmail()) != null) throw new OrganisationServiceException("user already exists");
        ModelMapper modelMapper = new ModelMapper();


        //BeanUtils.copyProperties(user,userEntity);
        OrganisationEntity organisationEntity = modelMapper.map(organisationDto, OrganisationEntity.class);


        organisationEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(organisationDto.getPassword()));
        OrganisationEntity storedUserDetails = organisationRepository.save(organisationEntity);
        //BeanUtils.copyProperties(storedUserDetails,returnValue);
        OrganisationDto returnValue = modelMapper.map(storedUserDetails, OrganisationDto.class);
        // Send an email message to user to verify their email address



        return returnValue;
    }

    @Override
    public OrganisationDto getUser(String email) {
        OrganisationEntity userEntity = organisationRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        OrganisationDto returnValue = new OrganisationDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }
/*
    @Override
    public OrganisationDto getUserByUserId(String userId) {
        OrganisationDto returnValue = new OrganisationDto();
        UserEntity userEntity = organisationRepository.findByUserId(userId);
        if (userEntity == null) throw new UserServiceException("user with ID : " + userId + " not found");

        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public OrganisationDto updateUser(String id, UserDto userDto) {
        UserEntity userEntity = organisationRepository.findByUserId(id);
        if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        UserDto returnVlue = new UserDto();

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());

        UserEntity updatedUser = organisationRepository.save(userEntity);
        BeanUtils.copyProperties(updatedUser, returnVlue);
        return returnVlue;
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = organisationRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        organisationRepository.delete(userEntity);
    }
*/

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
        OrganisationEntity userEntity = organisationRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),
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
