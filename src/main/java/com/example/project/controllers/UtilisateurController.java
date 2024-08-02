package com.example.project.controllers;

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


import com.example.project.entities.Utilisateur;
import com.example.project.entities.Utilisateur;
import com.example.project.services.UtilisateurService;
import com.example.project.services.UtilisateurService;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/utilisateur")
    public String index(@RequestParam(name = "search", required = false) String search, Model model) {
        List<Utilisateur> users = utilisateurService.getAllUsers(search);
        model.addAttribute("users", users);
        return "Utilisateur";
    }

    @GetMapping("/formUtilisateur")
    public String create(Model model) {
        model.addAttribute("user", new Utilisateur());
        return "formUtilisateur";
    }

    @PostMapping
    public String store(@ModelAttribute Utilisateur utilisateur) {
        utilisateurService.createUser(utilisateur);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        Optional<Utilisateur> user = utilisateurService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "users/show";
        } else {
            return "redirect:/users";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Utilisateur> user = utilisateurService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "users/edit";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute Utilisateur utilisateur) {
        utilisateurService.updateUser(id, utilisateur);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        utilisateurService.deleteUser(id);
        return "redirect:/users";
    }
}
