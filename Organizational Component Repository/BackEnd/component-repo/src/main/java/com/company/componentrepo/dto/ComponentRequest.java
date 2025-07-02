package com.company.componentrepo.dto;

import java.util.List;

public record ComponentRequest(
        String name,
        String description,
        List<String> technologies,
        List<String> tags
) { }
