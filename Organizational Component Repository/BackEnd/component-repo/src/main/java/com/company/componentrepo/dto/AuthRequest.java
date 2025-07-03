package com.company.componentrepo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = false)
public record AuthRequest(@NotBlank(message = "Email is required")
                          @Email(message = "Invalid email format")
                          String email,

                          @NotBlank(message = "Password is required")
                          String password) {}
