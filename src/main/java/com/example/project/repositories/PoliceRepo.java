package com.example.project.repositories;


import com.example.project.entities.Client;
import com.example.project.entities.Police;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PoliceRepo extends JpaRepository<Police, Long> {


//    Police findPoliceByCompteur5Chiffres(String chiffre);
    Page<Police> findByTypePoliceContains(String kw, Pageable pageable);
}
