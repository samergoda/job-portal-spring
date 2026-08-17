package com.jobportal.service;

import com.jobportal.dto.CompanyDto;
import com.jobportal.entity.Company;

import java.util.List;

public interface ICompanyService {

    List<CompanyDto> getAllCompanies();
}
