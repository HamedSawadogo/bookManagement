package com.bookmanagement.gestionlivres.repositories;

import com.bookmanagement.gestionlivres.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteDao extends JpaRepository<Compte,String> {
    /**
     * Rechercher les comptes d'un User
     * @param userId
     * @return
     */
   List<Compte>findComptesByUserId(String userId);

    /**
     * Recuperer le COmpte en FOnction de son ID
     * @param compteId
     * @return
     */
   Compte findCompteById(String compteId);
}
