package com.example.project.repositories;



import com.example.project.entities.Client;
import com.example.project.entities.TypeClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    List<Client> findByTypeClient(TypeClient typeClient);
    Page<Client> findByNomContains(String kw, Pageable pageable);
}
