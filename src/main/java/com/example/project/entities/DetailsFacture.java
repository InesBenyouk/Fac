package com.example.project.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetailsFacture {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numLigne;
    private float HTligne;
    private float TVAligne;
    private float TRligne;
    private float TTCligne;
    private String libellePrestation;

    @ManyToOne
    @JoinColumn(name = "entete_facture_id")
    private EnteteFacture enteteFacture;
    public void calculerLigne(float tauxTVA, float tauxTTR) {
        TVAligne = HTligne * tauxTVA;
        TRligne = HTligne * tauxTTR;
        TTCligne = HTligne + TVAligne + TRligne;
    }

}

