package com.company.componentrepo.entity;

import jakarta.persistence.*;


@Entity
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "component_id")
    private Component component;
}
