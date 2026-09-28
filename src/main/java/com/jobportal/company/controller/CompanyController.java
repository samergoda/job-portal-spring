package com.jobportal.company.controller;

import com.jobportal.company.dto.CompanyDto;
import com.jobportal.company.service.ICompanyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{v}/companies")
@RequiredArgsConstructor
public class CompanyController {
  private final ICompanyService companyService;

    @GetMapping(path = "/public", version = "v1")
    public ResponseEntity<List<CompanyDto>> getAllCompanies(){
       List<CompanyDto> companyList = companyService.getAllCompanies();
        return ResponseEntity.ok().body(companyList);
    }

    @GetMapping(path = "/page/public", version = "v1")
    public ResponseEntity<Page<CompanyDto>> getAllCompaniesWithPaginationAndSort(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<CompanyDto> companyPage = companyService
                .getAllCompanies(pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity.ok().body(companyPage);
    }

    @PostMapping(path = "/admin", version = "v1")
    public ResponseEntity<String> createCompany(@RequestBody @Valid CompanyDto companyDto) {
        boolean isCreated = companyService.createCompany(companyDto);
        if (isCreated) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Request processed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Request processing failed");
        }
    }

    @GetMapping(path = "/admin", version = "v1")
    public ResponseEntity<List<CompanyDto>> getAllCompaniesForAdmin() {
        List<CompanyDto> companyList = companyService.getAllCompaniesForAdmin();
        return ResponseEntity.ok().body(companyList);
    }

    @GetMapping(path = "/page/admin", version = "v1")
    public ResponseEntity<Page<CompanyDto>> getAllCompaniesForAdminWithPaginationAndSort(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<CompanyDto> companyPage = companyService
                .getAllCompaniesForAdmin(pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity.ok().body(companyPage);
    }

    @PutMapping(path = "/{id}/admin", version = "v1")
    public ResponseEntity<String> updateCompanyDetails(@PathVariable @NotBlank String id,
                                                       @RequestBody @Valid CompanyDto companyDto) {
        boolean isUpdated = companyService.updateCompanyDetails(Long.valueOf(id),companyDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("Company details updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update Company details");
        }
    }

    @DeleteMapping(path = "/{id}/admin", version = "v1")
    public ResponseEntity<String> deleteCompanyById(@PathVariable @NotBlank String id) {
        companyService.deleteCompanyById(Long.valueOf(id));
        return ResponseEntity.status(HttpStatus.OK).body("Company record deleted successfully.");
    }
}
