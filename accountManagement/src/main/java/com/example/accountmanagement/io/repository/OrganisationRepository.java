package com.example.accountmanagement.io.repository;

import com.example.accountmanagement.io.entity.OrganisationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrganisationRepository extends JpaRepository<OrganisationEntity,Long> {
    OrganisationEntity findByEmail(String email);
    //List<Organisation> findByVerified(boolean b);



}
