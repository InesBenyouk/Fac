package com.example.project.controllers;


import com.example.project.entities.Utilisateur;
import com.example.project.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UtilisateurController {
    @Autowired
    private UtilisateurService service;

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUsers() {
        return ResponseEntity.ok(service.findAllUsers());
    }
    // Autres endpoints
}
