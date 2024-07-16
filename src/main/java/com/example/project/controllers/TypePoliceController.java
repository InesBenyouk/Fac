package com.example.project.controllers;

import com.example.project.entities.TypePolice;
import com.example.project.services.TypePoliceService;
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
public class TypePoliceController {

    @Autowired
    private TypePoliceService typePoliceService;

    @GetMapping("/typePolices")
    public String listTypePolices(Model model,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "5") int size,
                                  @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<TypePolice> pageTypePolices = typePoliceService.findByLibelleContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listTypePolices", pageTypePolices.getContent());
        model.addAttribute("pages", new int[pageTypePolices.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "typePolices";
    }

    @GetMapping("/deleteTypePolice")
    public String deleteTypePolice(@RequestParam Long id, @RequestParam int page, @RequestParam String keyword) {
        typePoliceService.deleteById(id);
        return "redirect:/typePolices?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/formTypePolice")
    public String formTypePolice(Model model) {
        model.addAttribute("typePolice", new TypePolice());
        return "formTypePolice";
    }

    @PostMapping("/saveTypePolice")
    public String saveTypePolice(Model model, @Valid TypePolice typePolice, BindingResult bindingResult,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "") String keyword) {
        if (bindingResult.hasErrors()) return "formTypePolice";
        typePoliceService.save(typePolice);
        return "redirect:/typePolices?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/updateTypePolice")
    public String getUpdateForm(Model model, @RequestParam Long id, @RequestParam int page, @RequestParam String keyword) {
        Optional<TypePolice> typePolice = typePoliceService.findById(id);
        if (!typePolice.isPresent()) throw new RuntimeException("TypePolice not found!");
        model.addAttribute("typePolice", typePolice.get());
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editTypePolice";
    }
}

