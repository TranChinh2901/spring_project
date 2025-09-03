package com.example.spring_project.service;

import com.example.spring_project.entity.Company;
import com.example.spring_project.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Long id, Company companyDetails) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy company với ID: " + id));

        company.setName(companyDetails.getName());
        company.setAddress(companyDetails.getAddress());
        company.setPhone(companyDetails.getPhone());
        company.setEmail(companyDetails.getEmail());
        company.setWebsite(companyDetails.getWebsite());
        company.setDescription(companyDetails.getDescription());

        return companyRepository.save(company);
    }

    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy company với ID: " + id));

        Long userCount = companyRepository.countUsersByCompanyId(id);
        if (userCount > 0) {
            throw new RuntimeException("Không thể xóa company vì còn " + userCount + " user đang làm việc");
        }

        companyRepository.delete(company);
    }

    public List<Company> searchCompaniesByName(String name) {
        return companyRepository.findByNameContainingIgnoreCase(name);
    }

    public Optional<Company> findCompanyByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    public List<Company> searchCompaniesByAddress(String address) {
        return companyRepository.findByAddressContainingIgnoreCase(address);
    }

    public List<Company> getCompaniesWithUsers() {
        return companyRepository.findCompaniesWithUsers();
    }

    public Long countUsersByCompany(Long companyId) {
        return companyRepository.countUsersByCompanyId(companyId);
    }
}
