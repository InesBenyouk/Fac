package com.example.project.controllers;

import com.example.project.entities.Client;
import com.example.project.repositories.ClientRepo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class ClientController {
    private ClientRepo clientRepository;

    @GetMapping(path = "/index")
    public String clients(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "5") int size,
                          @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Client> pageClients = clientRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listClients", pageClients.getContent());
        model.addAttribute("pages", new int[pageClients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "clients";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, int page, String keyword) {
        clientRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/formClients")
    public String formClient(Model model) {
        model.addAttribute("client", new Client());
        return "formClients";
    }

    @PostMapping("/save")
    public String save(Model model, @Valid Client client, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword) {
        if (bindingResult.hasErrors()) return "formClients";
        //client.setNom(client.getNom() + " employee");
        clientRepository.save(client);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/update")
    public String getUpdateForm(Model model, Long id, int page, String keyword) {
        Optional<Client> client = clientRepository.findById(id);
        if (!client.isPresent()) throw new RuntimeException("Client not found!");
        model.addAttribute("client", client.get());
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editClients";
    }
}
