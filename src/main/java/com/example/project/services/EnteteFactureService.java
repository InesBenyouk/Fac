package com.example.project.services;

import com.example.project.entities.EnteteFacture;
import com.example.project.repositories.EnteteFactureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnteteFactureService {

    @Autowired
    private EnteteFactureRepo enteteFactureRepo;

    public List<EnteteFacture> findAll() {
        return enteteFactureRepo.findAll();
    }

    public Optional<EnteteFacture> findById(Long id) {
        return enteteFactureRepo.findById(id);
    }

    public EnteteFacture save(EnteteFacture enteteFacture) {
        calculateAmounts(enteteFacture);
        return enteteFactureRepo.save(enteteFacture);
    }

    public void deleteById(Long id) {
        enteteFactureRepo.deleteById(id);
    }

    public Page<EnteteFacture> findByClientNomContains(String keyword, PageRequest pageRequest) {
        return enteteFactureRepo.findByClientNomContains(keyword, pageRequest);
    }

    private void calculateAmounts(EnteteFacture enteteFacture) {
        float tauxTaxeRegionale = 0.04f; // 4%
        float tauxTVA = 0.16f; // 16%

        float montantHT = enteteFacture.getMontantHT();
        float montantTTR = montantHT * tauxTaxeRegionale;
        float montantTVA = montantHT * tauxTVA;
        float montantTTC = montantHT + montantTTR + montantTVA;

        enteteFacture.setMontantTTR(montantTTR);
        enteteFacture.setMontantTVA(montantTVA);
        enteteFacture.setMontantTTC(montantTTC);
    }
}



