package com.example.project.controllers;

import com.example.project.entities.*;
import com.example.project.repositories.PoliceRepo;
import com.example.project.repositories.PortRepo;
import com.example.project.services.RelevéService;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class RelevéController {


    private RelevéService releveService;
    private PoliceRepo policeRepo;
    private PortRepo portRepo;

    @GetMapping("/releves")
    public String listReleves(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "5") int size) {
        Page<Releve> pageReleves;

            pageReleves = releveService.findAll(PageRequest.of(page, size));

        model.addAttribute("listReleves", pageReleves.getContent());
        model.addAttribute("pages", new int[pageReleves.getTotalPages()]);
        model.addAttribute("currentPage", page);
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
    public String saveReleve(Model model, @Valid Releve releve, BindingResult bindingResult,
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
}

