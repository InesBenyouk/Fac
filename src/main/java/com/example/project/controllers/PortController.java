package com.example.project.controllers;

import com.example.project.entities.Port;
import com.example.project.services.PortService;
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
public class PortController {

    @Autowired
    private PortService portService;

    @GetMapping("/ports")
    public String listPorts(Model model,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "5") int size,
                            @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Port> pagePorts = portService.findByLibelleContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listPorts", pagePorts.getContent());
        model.addAttribute("pages", new int[pagePorts.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "ports";
    }

    @GetMapping("/deletePort")
    public String deletePort(@RequestParam Long id, @RequestParam int page, @RequestParam String keyword) {
        portService.deleteById(id);
        return "redirect:/ports?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/formPort")
    public String formPort(Model model) {
        model.addAttribute("port", new Port());
        return "formPort";
    }

    @PostMapping("/savePort")
    public String savePort(Model model, @Valid Port port, BindingResult bindingResult,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "") String keyword) {
        if (bindingResult.hasErrors()) return "formPort";
        portService.save(port);
        return "redirect:/ports?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/updatePort")
    public String getUpdateForm(Model model, @RequestParam Long id, @RequestParam int page, @RequestParam String keyword) {
        Optional<Port> port = portService.findById(id);
        if (!port.isPresent()) throw new RuntimeException("Port not found!");
        model.addAttribute("port", port.get());
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editPort";
    }
}
