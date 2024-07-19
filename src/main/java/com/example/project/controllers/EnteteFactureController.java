package com.example.project.controllers;

import com.example.project.entities.EnteteFacture;
import com.example.project.repositories.ClientRepo;
import com.example.project.repositories.EnteteFactureRepo;
import com.example.project.services.EnteteFactureService;
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
import java.util.Optional;

@Controller
@AllArgsConstructor
public class EnteteFactureController {


    private EnteteFactureService enteteFactureService;
private ClientRepo clientRepo;
private EnteteFactureRepo enteteFactureRepo;

    @GetMapping("/enteteFactures")
    public String listEnteteFactures(Model model,
                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "size", defaultValue = "5") int size,
                                     @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<EnteteFacture> pageEnteteFactures = enteteFactureService.findByClientNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listEnteteFactures", pageEnteteFactures.getContent());
        model.addAttribute("pages", new int[pageEnteteFactures.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "enteteFacture";
    }

    @GetMapping("/deleteEnteteFacture")
    public String deleteEnteteFacture(@RequestParam Long id, @RequestParam int page, @RequestParam String keyword) {
        enteteFactureService.deleteById(id);
        return "redirect:/enteteFactures?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/formEnteteFacture")
    public String formEnteteFacture(Model model) {
        model.addAttribute("enteteFacture", new EnteteFacture());
        model.addAttribute("clients",clientRepo.findAll());
        return "formEnteteFacture";
    }

    @PostMapping("/saveEnteteFacture")
    public String saveEnteteFacture(Model model, @Valid EnteteFacture enteteFacture, BindingResult bindingResult,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "") String keyword) {

//        enteteFactureService.save(enteteFacture);
        enteteFactureRepo.save(enteteFacture);
        return "redirect:/enteteFactures?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/updateEnteteFacture")
    public String getUpdateForm(Model model, @RequestParam Long id, @RequestParam int page, @RequestParam String keyword) {
        Optional<EnteteFacture> enteteFacture = enteteFactureService.findById(id);
        if (!enteteFacture.isPresent()) throw new RuntimeException("EnteteFacture not found!");
        model.addAttribute("enteteFacture", enteteFacture.get());
        model.addAttribute("page", page);
        model.addAttribute("clients",clientRepo.findAll());
        model.addAttribute("keyword", keyword);
        return "editEnteteFacture";
    }
}
