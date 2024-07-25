package com.example.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "enteteFacture")
    private Set<DetailsFacture> detailsFactures = new HashSet<>();
    public void calculerMontants(float tauxTVA, float tauxTTR) {
        montantTVA = montantHT * tauxTVA;
        montantTTR = montantHT * tauxTTR;
        montantTTC = montantHT + montantTVA + montantTTR;
    }
}

