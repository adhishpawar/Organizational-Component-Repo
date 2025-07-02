package com.company.componentrepo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String useDescription;
    private boolean isDisabled;
    @ElementCollection
    private List<String> technologiesUsed;
    @ElementCollection
    private List<String> tags = new ArrayList<>();
}
