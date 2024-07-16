package com.example.project.repositories;

import com.example.project.entities.Port;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortRepo extends JpaRepository<Port, Long> {

    Port findPortById(Long id);
    Page<Port> findByLibelleContains(String keyword, PageRequest pageRequest);
}

