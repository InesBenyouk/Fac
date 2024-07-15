package com.example.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
public class Police {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typePolice;
    private float tarif;
    private int compteur5Chiffres; // Pour suivre la consommation actuelle

    @ManyToOne
    private Utilisateur utilisateur;

    @OneToMany
    private Set<Releve> releves = new HashSet<>();

}