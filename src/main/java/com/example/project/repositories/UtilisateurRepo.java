package com.example.project.repositories;


import com.example.project.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface    UtilisateurRepo extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
}
