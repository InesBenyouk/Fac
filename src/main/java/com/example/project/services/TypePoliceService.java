package com.example.project.services;

import com.example.project.entities.TypePolice;
import com.example.project.repositories.TypePoliceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypePoliceService {

    @Autowired
    private TypePoliceRepo typePoliceRepo;

    public List<TypePolice> findAll() {
        return typePoliceRepo.findAll();
    }

    public Optional<TypePolice> findById(Long id) {
        return typePoliceRepo.findById(id);
    }

    public TypePolice save(TypePolice typePolice) {
        return typePoliceRepo.save(typePolice);
    }

    public void deleteById(Long id) {
        typePoliceRepo.deleteById(id);
    }

    public Page<TypePolice> findByLibelleContains(String keyword, PageRequest pageRequest) {
        return typePoliceRepo.findByLibelleContains(keyword, pageRequest);
    }
}

