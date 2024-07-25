package com.example.project.controllers;
import com.example.project.entities.*;
import com.example.project.repositories.DetailsFactureRepo;
import com.example.project.repositories.EnteteFactureRepo;
import com.example.project.repositories.PoliceRepo;
import com.example.project.repositories.PortRepo;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class RelevéController {


    private RelevéService releveService;
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



    @GetMapping("/genererFacture/{releveId}")
    public String genererFacture(@PathVariable Long releveId, Model model) {
        Releve releve = releveService.findById(releveId).orElseThrow();
        Client client = releve.getPolice().getClient();
        float tauxTVA = releve.getPolice().getRegion().getTauxTVA();
        float tauxTTR = releve.getPolice().getRegion().getTauxTaxeRegionale();

        releve.genererFacture(client, tauxTVA, tauxTTR, enteteFactureRepository, detailsFactureRepository);
        return "redirect:/releves"; // Redirigez vers la page de relevés après la génération de la facture
    }
}

