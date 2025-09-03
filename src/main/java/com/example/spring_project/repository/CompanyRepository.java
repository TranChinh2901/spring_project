package com.example.spring_project.repository;

import com.example.spring_project.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findByNameContainingIgnoreCase(String name);

    Optional<Company> findByEmail(String email);

    List<Company> findByAddressContainingIgnoreCase(String address);

    @Query("SELECT COUNT(u) FROM User u WHERE u.company.id = :companyId")
    Long countUsersByCompanyId(@Param("companyId") Long companyId);

    @Query("SELECT DISTINCT c FROM Company c WHERE SIZE(c.users) > 0")
    List<Company> findCompaniesWithUsers();
}
