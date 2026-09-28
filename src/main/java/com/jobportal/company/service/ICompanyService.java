package com.jobportal.company.service;

import com.jobportal.company.dto.CompanyDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICompanyService {

    List<CompanyDto> getAllCompanies();

    Page<CompanyDto> getAllCompanies(int pageNumber, int pageSize, String sortBy, String sortDir);

    List<CompanyDto> getAllCompaniesForAdmin();

    Page<CompanyDto> getAllCompaniesForAdmin(int pageNumber, int pageSize, String sortBy, String sortDir);

    void deleteCompanyById(Long id);

    boolean updateCompanyDetails(Long id, CompanyDto companyDto);

    boolean createCompany(CompanyDto companyDto);
}
