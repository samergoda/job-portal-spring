package com.jobportal.auth.dto;


import com.jobportal.common.dto.UserDto;

public record LoginResponseDto(String message, UserDto user, String jwtToken) {
}