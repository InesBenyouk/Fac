package com.example.project.repositories;



import com.example.project.entities.DetailsFacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DetailsFactureRepo extends JpaRepository<DetailsFacture, Long> {}
