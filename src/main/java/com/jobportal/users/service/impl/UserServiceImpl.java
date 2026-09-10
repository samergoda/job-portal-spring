package com.jobportal.users.service.impl;

import com.jobportal.common.constants.ApplicationConstants;
import com.jobportal.company.entity.Company;
import com.jobportal.company.repository.CompanyRepository;
import com.jobportal.role.entity.Role;
import com.jobportal.role.repository.RoleRepository;
import com.jobportal.users.dto.UserDto;
import com.jobportal.users.entity.JobPortalUser;
import com.jobportal.users.repository.JobPortalUserRepository;
import com.jobportal.users.service.IUserService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final JobPortalUserRepository jobPortalUserRepository;
    private final RoleRepository roleRepository;
    private final CompanyRepository companyRepository;


    @Override
    public Optional<JobPortalUser> getUserByEmail(String email) {
        return jobPortalUserRepository.findJobPortalUserByEmail(email);
    }

    @Transactional
    @Override
    public UserDto elevateToEmployer(Long userId) {
        JobPortalUser user =  jobPortalUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Check if user is already an employer
        if (ApplicationConstants.ROLE_EMPLOYER.equals(user.getRole().getName())) {
            return mapToUserDto(user);
        }
        // Check if user is already an admin
        if (ApplicationConstants.ROLE_ADMIN.equals(user.getRole().getName())) {
            throw new RuntimeException("Cannot elevate admin user to employer role");
        }

        // Find ROLE_EMPLOYER
        Role employerRole = roleRepository.findRoleByName(ApplicationConstants.ROLE_EMPLOYER)
                .orElseThrow(() -> new RuntimeException("ROLE_EMPLOYER not found"));
        user.setRole(employerRole);

        return mapToUserDto(user);
    }

    @Override
    public UserDto assignCompanyToEmployer(Long userId,Long companyId) {
        JobPortalUser user = jobPortalUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        // Verify user is an employer
        if (!ApplicationConstants.ROLE_EMPLOYER.equals(user.getRole().getName())) {
            throw new RuntimeException("User must be an employer to be assigned to a company");
        }
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + companyId));
        user.setCompany(company);
        // JobPortalUser updatedUser = userRepository.save(user);
        return mapToUserDto(user);
    }

    private UserDto mapToUserDto(JobPortalUser user) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        dto.setUserId(user.getId());
        dto.setRole(user.getRole() != null ? user.getRole().getName() : null);
        dto.setCompanyId(user.getCompany() != null ? user.getCompany().getId() : null);
        dto.setCompanyName(user.getCompany() != null ? user.getCompany().getName() : null);
        return dto;
    }
}
