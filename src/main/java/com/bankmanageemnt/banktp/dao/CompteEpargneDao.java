package com.bankmanageemnt.banktp.dao;
import com.bankmanageemnt.banktp.model.Compte;
import com.bankmanageemnt.banktp.model.CompteEpargne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteEpargneDao extends JpaRepository<CompteEpargne,String> {
}
