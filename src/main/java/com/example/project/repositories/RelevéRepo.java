package com.example.project.repositories;



import com.example.project.entities.Releve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelevéRepo extends JpaRepository<Releve, Long> {}