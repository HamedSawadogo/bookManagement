package com.bankmanageemnt.banktp.services;
import com.bankmanageemnt.banktp.model.Operation;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OperationService{
    void deposer(Double amount,String accountId);
    void retirer(Double montant,String accountId) throws NoSuchFieldException;
    void  transferer(Double montant,String accountA,String accountB);
    List<Operation>getAllOperations(int page,int size);

}
