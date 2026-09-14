package com.jobportal.profile.dto;

import com.jobportal.job.dto.JobDto;
import java.time.Instant;

public record JobApplicationDto(
        Long id,
        Long userId,
        String userName,
        String userEmail,
        String userMobileNumber,
        ProfileDto userProfile,
        JobDto job,
        Instant appliedAt,
        String status,
        String coverLetter,
        String notes
) {
}