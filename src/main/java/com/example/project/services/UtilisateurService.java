package com.example.project.services;


import com.example.project.entities.Utilisateur;
import com.example.project.repositories.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepo utilisateurRepository;

    public List<Utilisateur> findAllUsers() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur saveUser(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur getUserById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id : " + id));
    }
}

