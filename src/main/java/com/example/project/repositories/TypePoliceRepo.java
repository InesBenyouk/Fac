package com.example.project.repositories;

import com.example.project.entities.TypePolice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypePoliceRepo extends JpaRepository<TypePolice, Long> {
    Page<TypePolice> findByLibelleContains(String keyword, Pageable pageable);
}

