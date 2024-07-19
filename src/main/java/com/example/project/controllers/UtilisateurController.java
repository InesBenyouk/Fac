package com.example.project.controllers;

import com.example.project.entities.Utilisateur;
//import com.example.project.services.UtilisateurService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/utilisateurs")
//public class UtilisateurController {
//
//    @Autowired
//    private UtilisateurService utilisateurService;
//
//    @GetMapping
//    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
//        return ResponseEntity.ok(utilisateurService.findAll());
//    }
//
//    @PostMapping
//    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
//        return ResponseEntity.ok(utilisateurService.save(utilisateur));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getUtilisateurById(@PathVariable Long id) {
//        return utilisateurService.findById(id)
//                .map(utilisateur -> ResponseEntity.ok(utilisateur))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
//        Optional<Utilisateur> utilisateur = utilisateurService.findById(id);
//        if (utilisateur.isPresent()) {
//            utilisateurService.deleteById(id);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur newUtilisateur) {
//        return utilisateurService.findById(id)
//                .map(utilisateur -> {
//                    utilisateur.setNom(newUtilisateur.getNom());
//                    utilisateur.setEmail(newUtilisateur.getEmail());
//                    utilisateur.setRole(newUtilisateur.getRole());
//                    return ResponseEntity.ok(utilisateurService.save(utilisateur));
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//}
//
