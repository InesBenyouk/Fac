package com.example.project.controllers;

import com.example.project.entities.Releve;
import com.example.project.services.RelevéService;
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
import java.util.Date;
import java.util.Optional;

@Controller
public class RelevéController {

    @Autowired
    private RelevéService releveService;

    @GetMapping("/releves")
    public String listReleves(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "5") int size,
                              @RequestParam(name = "startDate", required = false) Date startDate,
                              @RequestParam(name = "endDate", required = false) Date endDate) {
        Page<Releve> pageReleves;
        if (startDate != null && endDate != null) {
            pageReleves = releveService.findByDateReleveBetween(startDate, endDate, PageRequest.of(page, size));
        } else {
            pageReleves = releveService.findAll(PageRequest.of(page, size));
        }
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
        return "redirect:/releves?page=" + page + "&startDate=" + startDate + "&endDate=" + endDate;
    }

    @GetMapping("/formReleve")
    public String formReleve(Model model) {
        model.addAttribute("releve", new Releve());
        return "formReleve";
    }

    @PostMapping("/saveReleve")
    public String saveReleve(Model model, @Valid Releve releve, BindingResult bindingResult,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(required = false) Date startDate,
                             @RequestParam(required = false) Date endDate) {
        if (bindingResult.hasErrors()) return "formReleve";
        releveService.save(releve);
        return "redirect:/releves?page=" + page + "&startDate=" + startDate + "&endDate=" + endDate;
    }

    @GetMapping("/updateReleve")
    public String getUpdateForm(Model model, @RequestParam Long id, @RequestParam int page, @RequestParam Date startDate, @RequestParam Date endDate) {
        Optional<Releve> releve = releveService.findById(id);
        if (!releve.isPresent()) throw new RuntimeException("Releve not found!");
        model.addAttribute("releve", releve.get());
        model.addAttribute("page", page);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "editReleve";
    }
}

