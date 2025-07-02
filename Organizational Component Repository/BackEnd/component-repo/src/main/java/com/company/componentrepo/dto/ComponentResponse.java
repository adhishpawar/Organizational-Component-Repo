package com.company.componentrepo.dto;

import java.util.List;

public record ComponentResponse
        (Long id,
         String name,
         String description,
         List<String> technologies,
         List<String> tags,
         boolean enabled) { }
