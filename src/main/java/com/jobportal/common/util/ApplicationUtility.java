package com.jobportal.common.util;

import com.jobportal.common.constants.ApplicationConstants;
import com.jobportal.users.entity.JobPortalUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ApplicationUtility {
    public static String getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser")) {
            return ApplicationConstants.SYSTEM;
        }
        Object principal = authentication.getPrincipal();
        String username;
        if (principal instanceof JobPortalUser jobPortalUser) {
            username = jobPortalUser.getEmail();
        } else {
            username = principal.toString(); // fallback
        }
        return username;
    }
}
