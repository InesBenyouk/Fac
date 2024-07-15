package com.example.project.repositories;

import com.example.project.entities.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortRepo extends JpaRepository<Port, Long> {

    Port findPortById(Long id);
}

