package com.jobportal.users.service;

import com.jobportal.users.dto.UserDto;
import com.jobportal.users.entity.JobPortalUser;

import java.util.Optional;

public interface IUserService {

 Optional<JobPortalUser> getUserByEmail(String email);

    /**
     * Search for a user by email address
     * @param email the email address to search for
     * @return Optional containing UserDto if found
     */
    Optional<UserDto> searchUserByEmail(String email);

    /**
     * Elevate a user's role to ROLE_EMPLOYER
     * @param userId the ID of the user to elevate
     * @return updated UserDto
     */
    UserDto elevateToEmployer(Long userId);

    /**
     * Assign a company to an employer
     * @param userId the ID of the employer
     * @param companyId the ID of the company to assign
     * @return updated UserDto
     */
    UserDto assignCompanyToEmployer(Long userId, Long companyId);
}
