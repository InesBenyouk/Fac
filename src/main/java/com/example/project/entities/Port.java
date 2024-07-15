package com.example.project.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;
import java.util.HashSet;


@Entity
@Table(name = "ports")
public class Port {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private float tauxTaxeRegional;

    @OneToMany
    private Set<Utilisateur> utilisateurs = new HashSet<>();

    public Port() {
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public float getTauxTaxeRegional() {
        return tauxTaxeRegional;
    }

    public void setTauxTaxeRegional(float tauxTaxeRegional) {
        this.tauxTaxeRegional = tauxTaxeRegional;
    }

    public Set<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
}

