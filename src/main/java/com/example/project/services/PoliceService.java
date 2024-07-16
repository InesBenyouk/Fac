package com.example.project.services;

import com.example.project.entities.Police;
import com.example.project.repositories.PoliceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceService {

    @Autowired
    private PoliceRepo policeRepo;

    public List<Police> findAll() {
        return policeRepo.findAll();
    }

    public Optional<Police> findById(Long id) {
        return policeRepo.findById(id);
    }

    public Police save(Police police) {
        return policeRepo.save(police);
    }

    public void deleteById(Long id) {
        policeRepo.deleteById(id);
    }
}
