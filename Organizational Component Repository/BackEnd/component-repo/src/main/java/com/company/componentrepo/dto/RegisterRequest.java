package com.company.componentrepo.dto;

import com.company.componentrepo.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = false)
public record RegisterRequest(String email, String password, Role role) {}

