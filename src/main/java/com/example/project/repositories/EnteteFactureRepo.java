package com.example.project.repositories;

import com.example.project.entities.EnteteFacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnteteFactureRepo extends JpaRepository<EnteteFacture, Long> {}
