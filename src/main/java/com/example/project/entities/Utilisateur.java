package com.example.project.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Email;
    private String nom;
    @Enumerated(EnumType.STRING)
    private role role;

    @OneToMany
    private Set<Police> polices = new HashSet<>();

    // Getters et setters
}


