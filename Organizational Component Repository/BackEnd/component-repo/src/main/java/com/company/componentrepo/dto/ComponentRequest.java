package com.company.componentrepo.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ComponentRequest(

        @NotBlank(message = "Component name is required")
        String name,

        @NotBlank(message = "Component description is required")
        String description,

        @NotEmpty(message = "At least one technology is required")
        List<@NotBlank(message = "Technology must not be blank") String> technologies,

        @Size(min = 1, max = 5, message = "You must provide 1 to 5 tags")
        List<@NotBlank(message = "Tag must not be blank") String> tags
) { }
