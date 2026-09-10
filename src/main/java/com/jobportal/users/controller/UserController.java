package com.jobportal.users.controller;

import com.jobportal.users.dto.UserDto;
import com.jobportal.users.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/{v}/users")
@RequiredArgsConstructor
public class UserController {

private final IUserService userService;

    @GetMapping("/search/admin")
    public ResponseEntity<?> searchUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }


    @PatchMapping("/{userId}/role/employer/admin")
    public ResponseEntity<?> elevateToEmployer(@PathVariable Long userId) {
        UserDto updatedUser = userService.elevateToEmployer(userId);
        return ResponseEntity.ok(updatedUser);
    }

    // /assign-company

    @PatchMapping("/{userId}/company/{companyId}/admin")
    public ResponseEntity<?> assignCompanyToEmployer(
            @PathVariable Long userId, @PathVariable Long companyId) {
        UserDto updatedUser = userService.assignCompanyToEmployer(userId, companyId);
        return ResponseEntity.ok(updatedUser);
    }
}
