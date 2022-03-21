package com.example.accountmanagement.io.repository;

import com.example.accountmanagement.io.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity,Long> {
    AdminEntity findByEmail(String email);
}
