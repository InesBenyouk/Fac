package com.example.project.repositories;

import com.example.project.entities.EnteteFacture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnteteFactureRepo extends JpaRepository<EnteteFacture, Long> {
    Page<EnteteFacture> findByClientNomContains(String keyword, Pageable pageable);

}
