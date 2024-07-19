package com.example.project.controllers;

import com.example.project.entities.Client;
import com.example.project.entities.Police;
import com.example.project.entities.Utilisateur;
import com.example.project.repositories.ClientRepo;
import com.example.project.repositories.PoliceRepo;
import com.example.project.repositories.UtilisateurRepo;
import com.example.project.services.PoliceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class PoliceController {

    private PoliceService policeService;
    private PoliceRepo policeRepo;
    private UtilisateurRepo utilisateurRepo;
    private ClientRepo clientRepo;

    @GetMapping("/polices")
    public String listPolices(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "5") int size,
                              @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Police> polices = policeRepo.findByTypePoliceContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listPolices", polices);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "polices";
    }

    @GetMapping("/deletePolice")
    public String deletePolice(@RequestParam Long id, @RequestParam int page, @RequestParam String keyword) {
        policeService.deleteById(id);
        return "redirect:/polices?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/formPolice")
    public String formPolice(Model model) {
        model.addAttribute("police", new Police());
        model.addAttribute("utilisateurs", utilisateurRepo.findAll());
        model.addAttribute("clients", clientRepo.findAll());
        return "formPolice";
    }

    @PostMapping("/savePolice")
    public String savePolice(Model model, @Valid Police police, BindingResult bindingResult,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "") String keyword) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("utilisateurs", utilisateurRepo.findAll());
            model.addAttribute("clients", clientRepo.findAll());
        }
        policeService.save(police);
        return "redirect:/polices?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/updatePolice")
    public String getUpdateForm(Model model, @RequestParam Long id, @RequestParam int page, @RequestParam String keyword) {
        Optional<Police> police = policeService.findById(id);
        if (!police.isPresent()) throw new RuntimeException("Police not found!");

        // Load lists for dropdowns
        List<Utilisateur> utilisateurs = utilisateurRepo.findAll();
        List<Client> clients = clientRepo.findAll();

        model.addAttribute("police", police.get());
        model.addAttribute("utilisateurs", utilisateurs);
        model.addAttribute("clients", clients);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editPolice";
    }

}
