package com.example.project.controllers;
import com.example.project.entities.*;
import com.example.project.repositories.*;
import com.example.project.services.EnteteFactureService;
import com.example.project.services.RelevéService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.*;

@Controller
@AllArgsConstructor
public class RelevéController {


    private RelevéService releveService;
    private RelevéRepo relevéRepo;
    private PoliceRepo policeRepo;
    private PortRepo portRepo;
    private EnteteFactureRepo enteteFactureRepository;
    private DetailsFactureRepo detailsFactureRepository;
    private EnteteFactureService enteteFactureService;

    @GetMapping("/releves")
    public String listReleves(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "5") int size,
                              @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                              @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Page<Releve> pageReleves;

        // Add logic here to filter based on startDate and endDate if provided

        pageReleves = releveService.findAll(PageRequest.of(page, size));

        model.addAttribute("listReleves", pageReleves.getContent());
        model.addAttribute("pages", new int[pageReleves.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "releve";
    }


    @GetMapping("/deleteReleve")
    public String deleteReleve(@RequestParam Long id, @RequestParam int page, @RequestParam Date startDate, @RequestParam Date endDate) {
        releveService.deleteById(id);
        return "redirect:/releves?page=" + page;
    }

    @GetMapping("/formReleve")
    public String formReleve(Model model) {
        List<Police> polices = policeRepo.findAll();
        List<Port> ports = portRepo.findAll();
        model.addAttribute("releve", new Releve());
        model.addAttribute("polices", polices);
        model.addAttribute("ports", ports);
        return "formReleve";
    }

    @PostMapping("/saveReleve")
    public String saveReleve(Model model, @Valid @ModelAttribute("releve") Releve releve, BindingResult bindingResult,
                             @RequestParam(defaultValue = "0") int page) {
        releve.setPortId(1L);
        if (bindingResult.hasErrors()) {
            List<Police> polices = policeRepo.findAll();
            List<Port> ports = portRepo.findAll();
            model.addAttribute("polices", polices);
            model.addAttribute("ports", ports);
            return "formReleve";
        }

        releveService.save(releve);
        return "redirect:/releves?page=" + page;
    }


    @GetMapping("/updateReleve")
    public String getUpdateForm(Model model, @RequestParam Long id, @RequestParam int page) {
        Optional<Releve> releve = releveService.findById(id);
        if (!releve.isPresent()) throw new RuntimeException("Releve not found!");
        List<Police> polices = policeRepo.findAll();
        List<Port> ports = portRepo.findAll();
        model.addAttribute("releve", releve.get());
        model.addAttribute("page", page);
        model.addAttribute("polices", polices);
        model.addAttribute("ports", ports);
        return "editReleve";
    }


//    @GetMapping("/generateFacture")
//   public String generateFacture(@RequestParam(name = "id") Long id, Model model) {
//      Releve releve=relevéRepo.findById(id).orElseThrow();
//      float consommation= releve.getConsommation();
//       float tarif= releve.getPolice().getTarif();
//       EnteteFacture enteteFacture=new EnteteFacture();
//       enteteFacture.setId(1L);
//       enteteFacture.setMontantTTC(1000.0f);
//       enteteFacture.setMontantHT(consommation*tarif);
//       enteteFacture.setMontantTVA(200.0f);
//       enteteFacture.setMontantTTR(950.0f);
//       enteteFacture.setDateEmission(new Date());
//        enteteFacture.setDatePaiement(new Date());
//        enteteFacture.setDateAnnulation(null);
//        DetailsFacture detailsFacture=new DetailsFacture();
//        detailsFacture.setNumLigne(1);
//        detailsFacture.setHTligne(500.0f);
//        detailsFacture.setTVAligne(100.0f);
//        detailsFacture.setTRligne(50.0f);
//        detailsFacture.setTTCligne(650.0f);
//        detailsFacture.setLibellePrestation("Consulting Services");
//        Set<DetailsFacture> detailsFactures=new HashSet<>();
//        detailsFactures.add(detailsFacture);
//        enteteFacture.setDetailsFactures(detailsFactures);
//        enteteFactureRepository.save(enteteFacture);
//        model.addAttribute("enteteFacture", enteteFacture);
//        return "detailsFacture";
//   }



//    @Autowired
//    private RelevéRepo releveRepo;
//
//    @Autowired
//    private EnteteFactureRepo enteteFactureRepo;
//
//    @Autowired
//    private DetailsFactureRepo detailsFactureRepo;
//
//    @GetMapping("/generateFacture")
//    public String generateFacture(@RequestParam(name = "id") Long id, Model model) {
//        Releve releve = releveRepo.findById(id).orElseThrow();
//
//        // Calcul de la consommation
//        float consommation = (releve.getNouvelleLecture() - releve.getAncienneLecture()) * 100;
//
//        // Obtention du tarif
//        float tarif = releve.getPolice().getTarif();
//
//        // Calcul du montant HT
//        float montantHT = consommation * tarif;
//
//        // Calcul des montants TVA, TTR, et TTC
//        float tauxTVA = 0.16f; // Exemple de taux de TVA (16%)
//        float tauxTTR = 0.04f; // Exemple de taux de taxe régionale (4%)
//        float montantTVA = montantHT * tauxTVA;
//        float montantTTR = montantHT * tauxTTR;
//        float montantTTC = montantHT + montantTVA + montantTTR;
//
//        // Création de l'entête de facture
//        EnteteFacture enteteFacture = new EnteteFacture();
//        enteteFacture.setMontantHT(montantHT);
//        enteteFacture.setMontantTVA(montantTVA);
//        enteteFacture.setMontantTTR(montantTTR);
//        enteteFacture.setMontantTTC(montantTTC);
//        enteteFacture.setDateEmission(new Date());
//        enteteFacture.setDatePaiement(new Date());
//
//        // Création des détails de la facture
//        DetailsFacture detailsFacture = new DetailsFacture();
//        detailsFacture.setNumLigne(1);
//        detailsFacture.setHTligne(montantHT);
//        detailsFacture.setTVAligne(montantTVA);
//        detailsFacture.setTRligne(montantTTR);
//        detailsFacture.setTTCligne(montantTTC);
//        detailsFacture.setLibellePrestation("Consommation");
//        detailsFacture.setEnteteFacture(enteteFacture);
//
//        Set<DetailsFacture> detailsFactures = new HashSet<>();
//        detailsFactures.add(detailsFacture);
//        enteteFacture.setDetailsFactures(detailsFactures);
//
//        // Sauvegarde de l'entête de facture et des détails
//        enteteFactureRepo.save(enteteFacture);
//        detailsFactureRepo.save(detailsFacture);
//
//        // Ajout de l'entête de facture au modèle
//        model.addAttribute("enteteFacture", enteteFacture);
//
//        return "detailsFacture"; // Rediriger vers une vue appropriée
//    }

    @Autowired
    private RelevéRepo releveRepo;

    @Autowired
    private EnteteFactureRepo enteteFactureRepo;

    @Autowired
    private DetailsFactureRepo detailsFactureRepo;
    @GetMapping("/generateFacture")
    public String generateFacture(@RequestParam(name = "id") Long id, Model model) {
        Releve releve = releveRepo.findById(id).orElseThrow();

        // Calcul de la consommation
        float consommation = (releve.getNouvelleLecture() - releve.getAncienneLecture()) * 100;

        // Obtention du tarif
        float tarif = releve.getPolice().getTarif();

        // Calcul du montant HT
        float montantHT = consommation * tarif;
        System.out.println(consommation);
        System.out.println(tarif);
        System.out.println(montantHT);
        // Calcul des montants TVA, TTR, et TTC
        float tauxTVA = 0.16f; // Exemple de taux de TVA (16%)
        float tauxTTR = 0.04f; // Exemple de taux de taxe régionale (4%)
        float montantTVA = montantHT * tauxTVA;
        float montantTTR = montantHT * tauxTTR;
        float montantTTC = montantHT + montantTVA + montantTTR;

        System.out.println(montantTVA);
        System.out.println(montantTTR);
        System.out.println(montantTTC);
        // Création de l'entête de facture
        EnteteFacture enteteFacture = new EnteteFacture();
        enteteFacture.setId(1L); // Vous devez définir une méthode pour générer l'ID unique
        enteteFacture.setMontantHT(montantHT);
        enteteFacture.setMontantTVA(montantTVA);
        enteteFacture.setMontantTTR(montantTTR);
        enteteFacture.setMontantTTC(montantTTC);
        enteteFacture.setDateEmission(new Date());
        enteteFacture.setDatePaiement(new Date());
        enteteFacture.setDateAnnulation(null);

        // Création des détails de la facture
        DetailsFacture detailsFacture = new DetailsFacture();
        detailsFacture.setNumLigne(1);
        detailsFacture.setHTligne(montantHT);
        detailsFacture.setTVAligne(montantTVA);
        detailsFacture.setTRligne(montantTTR);
        detailsFacture.setTTCligne(montantTTC);
        detailsFacture.setLibellePrestation("Consommation");

        Set<DetailsFacture> detailsFactures = new HashSet<>();
        detailsFactures.add(detailsFacture);
        enteteFacture.setDetailsFactures(detailsFactures);

        // Sauvegarde de l'entête de facture et des détails
        enteteFactureRepo.save(enteteFacture);


        // Ajout de l'entête de facture au modèle
        model.addAttribute("enteteFacture", enteteFacture);
        return "detailsFacture"; // Rediriger vers une vue appropriée
    }
}


