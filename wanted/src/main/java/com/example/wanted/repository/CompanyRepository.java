package com.example.wanted.repository;

import com.example.wanted.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface CompanyRepository extends JpaRepository<Company, String> {
    Optional<Company> findByCompanyId(Long id);
}
