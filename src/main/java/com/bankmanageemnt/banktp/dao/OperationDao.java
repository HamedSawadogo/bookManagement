package com.bankmanageemnt.banktp.dao;
import com.bankmanageemnt.banktp.model.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationDao extends JpaRepository<Operation,Long> {
    //renvoie la liste des Pages D"opérations
    Page<Operation>findAll(Pageable pageable);
}
