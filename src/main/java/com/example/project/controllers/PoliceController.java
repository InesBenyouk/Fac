package com.example.project.controllers;

import com.example.project.entities.Police;
import com.example.project.services.PoliceService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PoliceController {

    @Autowired
    private PoliceService policeService;

    @GetMapping("/polices")
    public String listPolices(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "5") int size,
                              @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        List<Police> polices = policeService.findAll(); // TODO: Add pagination and search by keyword
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
        return "formPolice";
    }

    @PostMapping("/savePolice")
    public String savePolice(Model model, @Valid Police police, BindingResult bindingResult,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "") String keyword) {
        if (bindingResult.hasErrors()) return "formPolice";
        policeService.save(police);
        return "redirect:/polices?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/updatePolice")
    public String getUpdateForm(Model model, @RequestParam Long id, @RequestParam int page, @RequestParam String keyword) {
        Optional<Police> police = policeService.findById(id);
        if (!police.isPresent()) throw new RuntimeException("Police not found!");
        model.addAttribute("police", police.get());
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editPolice";
    }
}
