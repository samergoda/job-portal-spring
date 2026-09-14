package com.jobportal.profile.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jobportal.job.dto.JobDto;
import com.jobportal.profile.dto.ApplyJobRequestDto;
import com.jobportal.profile.dto.JobApplicationDto;
import com.jobportal.profile.dto.ProfileDto;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProfileService {
    ProfileDto createOrUpdateProfile(String userEmail, String profileJson,
                                     MultipartFile profilePicture, MultipartFile resume) throws JsonProcessingException;

    ProfileDto getProfile(String userEmail);

    ProfileDto getProfilePicture(String userEmail);

    ProfileDto getResume(String userEmail);

    JobDto saveJob(String userEmail, Long jobId);

    void unsaveJob(String userEmail, Long jobId);

    List<JobDto> getSavedJobs(String userEmail);

    JobApplicationDto applyForJob(String userEmail, @Valid ApplyJobRequestDto request);

    void withdrawApplication(String userEmail, Long jobId);

    List<JobApplicationDto> getJobSeekerApplications(String userEmail);
}
