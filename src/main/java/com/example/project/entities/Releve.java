package com.example.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Releve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateRelevé;
    private float ancienneLecture;
    private float nouvelleLecture;
    private Long portId;
    private Date dateDebut;
    private Date dateFin;
    private float quantiteConsommee;

    @ManyToOne
    private Police police;

    public void setQuantiteConsommee() {
        int compteur5Chiffres = police.getCompteur5Chiffres();
        float difference = nouvelleLecture - ancienneLecture;
        if (difference < 0) {
            difference += Math.pow(10, compteur5Chiffres);
        }
        this.quantiteConsommee = difference;
    }
}


