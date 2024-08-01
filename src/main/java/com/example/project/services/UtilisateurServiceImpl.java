package com.example.project.services;

import com.example.project.entities.Utilisateur;
import com.example.project.repositories.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepo utilisateurRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<Utilisateur> getAllUsers(String search) {
        if (search == null || search.isEmpty()) {
            return utilisateurRepository.findAll();
        } else {
            return utilisateurRepository.searchUsers(search);
        }
    }

    @Override
    public Utilisateur createUser(Utilisateur utilisateur) {
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateurRepository.save(utilisateur);
        return utilisateur;
    }

    @Override
    public Optional<Utilisateur> getUserById(Long id) {
        return utilisateurRepository.findById(id);
    }

    @Override
    public Utilisateur updateUser(Long id, Utilisateur utilisateur) {
        Optional<Utilisateur> existingUserOpt = utilisateurRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            Utilisateur existingUser = existingUserOpt.get();
            existingUser.setNom(utilisateur.getNom());
            existingUser.setPrenom(utilisateur.getPrenom());
            existingUser.setEmail(utilisateur.getEmail());
            if (utilisateur.getPassword() != null && !utilisateur.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
            }
            existingUser.setTelephone(utilisateur.getTelephone());
            existingUser.setRole(utilisateur.getRole());
            utilisateurRepository.save(existingUser);
        }
        return utilisateur;
    }

    @Override
    public void deleteUser(Long id) {
        utilisateurRepository.deleteById(id);
    }
}


