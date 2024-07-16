package com.example.project.services;

import com.example.project.entities.Releve;
import com.example.project.repositories.RelevéRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RelevéService {

    @Autowired
    private RelevéRepo releveRepo;

   

    public Optional<Releve> findById(Long id) {
        return releveRepo.findById(id);
    }

    public Releve save(Releve releve) {
        releve.setQuantiteConsommee();
        return releveRepo.save(releve);
    }

    public void deleteById(Long id) {
        releveRepo.deleteById(id);
    }

    public Page<Releve> findByDateReleveBetween(Date dateDebut, Date dateFin, PageRequest pageRequest) {
        return releveRepo.findByDateRelevéBetween(dateDebut, dateFin, pageRequest);
    }


    public Page<Releve> findAll(PageRequest pageRequest) {
        return releveRepo.findAll(pageRequest);
    }
}
