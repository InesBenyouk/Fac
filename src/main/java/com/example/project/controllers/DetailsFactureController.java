package com.example.project.controllers;

import com.example.project.entities.DetailsFacture;
import com.example.project.services.DetailsFactureService;
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
public class DetailsFactureController {

    @Autowired
    private DetailsFactureService detailsFactureService;

    @GetMapping("/detailsFactures")
    public String listDetailsFactures(Model model,
                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "5") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<DetailsFacture> pageDetailsFactures = detailsFactureService.findAll(pageRequest);
        model.addAttribute("listDetailsFactures", pageDetailsFactures.getContent());
        model.addAttribute("pages", new int[pageDetailsFactures.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return "detailsFactures";
    }

    @GetMapping("/deleteDetailsFacture")
    public String deleteDetailsFacture(@RequestParam Long id, @RequestParam int page) {
        detailsFactureService.deleteById(id);
        return "redirect:/detailsFactures?page=" + page;
    }

    @GetMapping("/formDetailsFacture")
    public String formDetailsFacture(Model model) {
        model.addAttribute("detailsFacture", new DetailsFacture());
        return "formDetailsFacture";
    }

    @PostMapping("/saveDetailsFacture")
    public String saveDetailsFacture(Model model, @Valid DetailsFacture detailsFacture, BindingResult bindingResult,
                                     @RequestParam(defaultValue = "0") int page) {
        if (bindingResult.hasErrors()) return "formDetailsFacture";
        detailsFactureService.save(detailsFacture);
        return "redirect:/detailsFactures?page=" + page;
    }

    @GetMapping("/updateDetailsFacture")
    public String getUpdateForm(Model model, @RequestParam Long id, @RequestParam int page) {
        Optional<DetailsFacture> detailsFacture = detailsFactureService.findById(id);
        if (!detailsFacture.isPresent()) throw new RuntimeException("DetailsFacture not found!");
        model.addAttribute("detailsFacture", detailsFacture.get());
        model.addAttribute("page", page);
        return "editDetailsFacture";
    }
}
