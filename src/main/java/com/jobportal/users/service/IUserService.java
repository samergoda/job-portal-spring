package com.jobportal.users.service;

import com.jobportal.users.dto.UserDto;
import com.jobportal.users.entity.JobPortalUser;

import java.util.Optional;

public interface IUserService {

 Optional<JobPortalUser> getUserByEmail(String email);

    UserDto elevateToEmployer(Long userId);
    UserDto assignCompanyToEmployer(Long userId, Long companyId);

}
