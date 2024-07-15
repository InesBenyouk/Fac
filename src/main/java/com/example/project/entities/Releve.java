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
@NamedEntityGraph
@Getter
@Setter
public class Releve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateRelevé;
    private float quantiteEau;
    private float quantiteElectricite;
    private Date dateDebut;
    private Date dateFin;

    @ManyToOne
    private Police police;

    // Getters et setters
}

