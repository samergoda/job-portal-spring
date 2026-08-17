package com.jobportal.service.impl;


import com.jobportal.dto.CompanyDto;
import com.jobportal.entity.Company;
import com.jobportal.repository.CompanyRepository;
import com.jobportal.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;


    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companyList = companyRepository.findAll();
        return companyList.stream().map(this::transformToDto).collect(Collectors.toList());
    }
    private CompanyDto transformToDto(Company company) {
        return new CompanyDto(company.getId(), company.getName(), company.getLogo(),
                company.getIndustry(), company.getSize(), company.getRating(),
                company.getLocations(), company.getFounded(), company.getDescription(),
                company.getEmployees(), company.getWebsite(), company.getCreatedAt());
    }
}
