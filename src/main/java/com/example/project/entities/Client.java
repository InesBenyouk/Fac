package com.example.project.entities;
import com.example.project.entities.EnteteFacture;
import com.example.project.entities.TypeClient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.util.Set;


@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;
    @Enumerated(EnumType.STRING)
    private TypeClient typeClient;
    private String ICE;
    private String CIN;


    @OneToMany(mappedBy = "client")
    private Set<EnteteFacture> factures;


    /* Getters et setters */
}
