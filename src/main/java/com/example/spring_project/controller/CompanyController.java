package com.example.spring_project.controller;

import com.example.spring_project.entity.Company;
import com.example.spring_project.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "*")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Optional<Company> company = companyService.getCompanyById(id);
        if (company.isPresent()) {
            return ResponseEntity.ok(company.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company) {
        Company createdCompany = companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @Valid @RequestBody Company companyDetails) {
        try {
            Company updatedCompany = companyService.updateCompany(id, companyDetails);
            return ResponseEntity.ok(updatedCompany);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        try {
            companyService.deleteCompany(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Company>> searchCompanies(@RequestParam(required = false) String name) {
        if (name != null && !name.trim().isEmpty()) {
            List<Company> companies = companyService.searchCompaniesByName(name);
            return ResponseEntity.ok(companies);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/with-users")
    public ResponseEntity<List<Company>> getCompaniesWithUsers() {
        List<Company> companies = companyService.getCompaniesWithUsers();
        return ResponseEntity.ok(companies);
    }
    @GetMapping("/{id}/user-count")
    public ResponseEntity<Long> getUserCountByCompany(@PathVariable Long id) {
        try {
            Long count = companyService.countUsersByCompany(id);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
