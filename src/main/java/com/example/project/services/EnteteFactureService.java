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
        return enteteFactureRepo.save(enteteFacture);
    }

    public void deleteById(Long id) {
        enteteFactureRepo.deleteById(id);
    }

    public Page<EnteteFacture> findByClientNomContains(String keyword, PageRequest pageRequest) {
        return enteteFactureRepo.findByClientNomContains(keyword, pageRequest);
    }
}


