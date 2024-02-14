package com.bankmanageemnt.banktp.dao;

import com.bankmanageemnt.banktp.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteDao extends JpaRepository<Compte,String> {
    Compte findCompteByNumCompte(String numCompte);
}
