package com.example.project.services;

import com.example.project.entities.Client;
import com.example.project.entities.EnteteFacture;
import com.example.project.entities.Releve;
import com.example.project.repositories.EnteteFactureRepo;
import com.example.project.repositories.RelevéRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RelevéService {

    @Autowired
    private RelevéRepo releveRepo;

   

    public Optional<Releve> findById(Long id) {
        return releveRepo.findById(id);
    }

    public Releve save(Releve releve) {
        releve.setQuantiteConsommee();
        return releveRepo.save(releve);
    }

    public void deleteById(Long id) {
        releveRepo.deleteById(id);
    }

    public Page<Releve> findByDateReleveBetween(Date dateDebut, Date dateFin, PageRequest pageRequest) {
        return releveRepo.findByDateRelevéBetween(dateDebut, dateFin, pageRequest);
    }


    public Page<Releve> findAll(PageRequest pageRequest) {
        return releveRepo.findAll(pageRequest);
    }


    @Autowired
    private EnteteFactureRepo enteteFactureRepo;
    public EnteteFacture genererFactureAutomatique(Long releveId, Client client) {
        // Récupérer le relevé par son ID
        Releve releve = releveRepo.findById(releveId).orElseThrow(() -> new RuntimeException("Relevé introuvable"));

        // Définir les taux pour la TVA et la taxe régionale
        float tauxTVA = 0.14f; // 14%
        float tauxTTR = 0.04f; // 4%

        // Générer la facture à partir du relevé
        EnteteFacture facture = releve.genererFacture(client, tauxTVA, tauxTTR);

        // Sauvegarder la facture dans la base de données
        enteteFactureRepo.save(facture);

        // Retourner la facture générée
        return facture;
    }
}
