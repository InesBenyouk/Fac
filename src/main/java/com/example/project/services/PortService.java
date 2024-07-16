package com.example.project.services;

import com.example.project.entities.Port;
import com.example.project.repositories.PortRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortService {

    @Autowired
    private PortRepo portRepo;

    public List<Port> findAll() {
        return portRepo.findAll();
    }

    public Optional<Port> findById(Long id) {
        return portRepo.findById(id);
    }

    public Port save(Port port) {
        return portRepo.save(port);
    }

    public void deleteById(Long id) {
        portRepo.deleteById(id);
    }

    public Page<Port> findByLibelleContains(String keyword, PageRequest pageRequest) {
        return portRepo.findByLibelleContains(keyword, pageRequest);
    }
}

