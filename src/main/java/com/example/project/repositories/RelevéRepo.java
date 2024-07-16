package com.example.project.repositories;



import com.example.project.entities.Releve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RelevéRepo extends JpaRepository<Releve, Long> {

 Page<Releve> findByDateRelevéBetween(Date dateDebut, Date dateFin, Pageable pageable);
}
