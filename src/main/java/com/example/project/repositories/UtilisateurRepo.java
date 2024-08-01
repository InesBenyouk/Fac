package com.example.project.repositories;



import com.example.project.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepo extends JpaRepository<Utilisateur, Long> {

   @Query("SELECT u FROM Utilisateur u WHERE u.nom LIKE %:search% OR u.prenom LIKE %:search%  OR u.telephone LIKE %:search% OR u.role LIKE %:search%")
//   List<Utilisateur> findBySearchTerm(@Param("search") String search);

        List<Utilisateur> searchUsers(String search);
}

