package com.example.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data
public class EnteteFacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float montantTTC;
    private float montantHT;
    private float montantTVA;
    private float montantTTR;
    private Date dateEmission;
    private Date datePaiement;
    private Date dateAnnulation;

    @ManyToOne
    private Client client;

    // Getters et setters
}

