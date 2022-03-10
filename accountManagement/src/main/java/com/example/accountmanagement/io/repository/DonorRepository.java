package com.example.accountmanagement.io.repository;

import com.example.accountmanagement.io.entity.DonorEntity;
import com.example.accountmanagement.io.entity.OrganisationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<DonorEntity,Long> {
    DonorEntity findByEmail(String email);
}
