package com.example.project.services;

import com.example.project.entities.DetailsFacture;
import com.example.project.repositories.DetailsFactureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailsFactureService {

    @Autowired
    private DetailsFactureRepo detailsFactureRepo;

    public List<DetailsFacture> findAll() {
        return detailsFactureRepo.findAll();
    }

    public Optional<DetailsFacture> findById(Long id) {
        return detailsFactureRepo.findById(id);
    }

    public DetailsFacture save(DetailsFacture detailsFacture) {
        calculateAmounts(detailsFacture);
        return detailsFactureRepo.save(detailsFacture);
    }

    public void deleteById(Long id) {
        detailsFactureRepo.deleteById(id);
    }

    private void calculateAmounts(DetailsFacture detailsFacture) {
        float tauxTVA = 0.16f; // 16%
        float tauxTaxeRegionale = 0.04f; // 4%

        float HTligne = detailsFacture.getHTligne();
        float TRligne = HTligne * tauxTaxeRegionale;
        float TVAligne = HTligne * tauxTVA;
        float TTCligne = HTligne + TRligne + TVAligne;

        detailsFacture.setTRligne(TRligne);
        detailsFacture.setTVAligne(TVAligne);
        detailsFacture.setTTCligne(TTCligne);
    }

    public Page<DetailsFacture> findAll(PageRequest pageRequest) {
        return detailsFactureRepo.findAll(pageRequest);
    }}

