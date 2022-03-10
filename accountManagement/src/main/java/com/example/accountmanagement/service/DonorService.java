package com.example.accountmanagement.service;

import com.example.accountmanagement.exceptions.OrganisationServiceException;
import com.example.accountmanagement.io.entity.DonorEntity;
import com.example.accountmanagement.io.entity.OrganisationEntity;
import com.example.accountmanagement.io.repository.DonorRepository;
import com.example.accountmanagement.shared.DonorDto;
import com.example.accountmanagement.shared.OrganisationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DonorService {
    @Autowired
    DonorRepository donorRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public DonorDto createDonor(DonorDto donorDto) {
        if (donorRepository.findByEmail(donorDto.getEmail()) != null) throw new OrganisationServiceException("user already exists");
        ModelMapper modelMapper = new ModelMapper();
        DonorEntity donorEntity = modelMapper.map(donorDto, DonorEntity.class);

        donorEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(donorDto.getPassword()));
        DonorEntity storedDonorDetails = donorRepository.save(donorEntity);
        DonorDto returnValue = modelMapper.map(storedDonorDetails, DonorDto.class);
        // Send an email message to user to verify their email address



        return returnValue;
    }
}
