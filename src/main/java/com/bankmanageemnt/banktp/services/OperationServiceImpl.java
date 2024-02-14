package com.bankmanageemnt.banktp.services;

import com.bankmanageemnt.banktp.dao.CompteCourantDao;
import com.bankmanageemnt.banktp.dao.CompteDao;
import com.bankmanageemnt.banktp.dao.CompteEpargneDao;
import com.bankmanageemnt.banktp.dao.OperationDao;
import com.bankmanageemnt.banktp.enums.OperationType;
import com.bankmanageemnt.banktp.model.Compte;
import com.bankmanageemnt.banktp.model.CompteCourant;
import com.bankmanageemnt.banktp.model.CompteEpargne;
import com.bankmanageemnt.banktp.model.Operation;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class OperationServiceImpl implements OperationService {

    private final OperationDao operationDao;
    private final CompteDao compteDao;

    private final CompteEpargneDao compteEpargneDao;
    private final CompteCourantDao compteCourantDao;

    public OperationServiceImpl(
        OperationDao operationDao,
        CompteDao compteDao,
        CompteEpargneDao compteEpargneDao,
        CompteCourantDao compteCourantDao
    ) {
        this.operationDao = operationDao;
        this.compteDao = compteDao;
        this.compteEpargneDao = compteEpargneDao;
        this.compteCourantDao = compteCourantDao;
    }

    @Override
    public void deposer(Double amount, String accountId) {
        Compte compte=this.compteDao.findCompteByNumCompte(accountId);
        compte.setSolde(compte.getSolde()+amount);
        Operation operation = new Operation();
        operation.setOperationDate(new Date());
        operation.setOperationType(OperationType.VERSEMENT);
        operation.setMontant(amount);
        operation.setCompte(compte);
        this.operationDao.save(operation);


        /**if (compte instanceof CompteEpargne) {
            CompteEpargne compteEpargne = (CompteEpargne) compte;
            compteEpargne.setSolde(compteEpargne.getSolde() + amount);

            operation.setOperationDate(new Date());
            operation.setOperationType(OperationType.VERSEMENT);
            operation.setMontant(amount);
            operation.setCompte(compteEpargne);
            this.operationDao.save(operation);

            this.compteEpargneDao.save(compteEpargne);
        } else if (compte instanceof CompteCourant) {
            CompteCourant compteCourant = (CompteCourant) compte;
            compteCourant.setSolde(compteCourant.getSolde() + amount);

            operation.setOperationDate(new Date());
            operation.setOperationType(OperationType.VERSEMENT);
            operation.setMontant(amount);
            operation.setCompte(compteCourant);
            this.operationDao.save(operation);

            this.compteCourantDao.save(compteCourant);
        }**/
    }
    @Override
    public void retirer(Double montant, String accountId) throws NoSuchFieldException {
        Compte compte=this.compteDao.findCompteByNumCompte(accountId);
        if(compte.getSolde()<montant)throw new NoSuchFieldException("");
        Operation operation = new Operation();
        operation.setOperationDate(new Date());
        operation.setOperationType(OperationType.VERSEMENT);
        operation.setMontant(montant);
        operation.setCompte(compte);
        this.operationDao.save(operation);
       /** if(compte instanceof CompteEpargne){
            CompteEpargne compteEpargne=(CompteEpargne) compte;
            compteEpargne.setSolde(compteEpargne.getSolde()-montant);
            this.compteEpargneDao.save(compteEpargne);
        }else if(compte instanceof CompteCourant){
            CompteCourant compteCourant=(CompteCourant)compte;

            if(compteCourant.getSolde()<montant)throw new NoSuchFieldException("amount not suffisant!!");
            compteCourant.setSolde(compteCourant.getSolde()-montant);
            this.compteCourantDao.save(compteCourant);
        }**/
    }

    @Override
    public void transferer(Double montant, String accountA, String accountB) {

    }
    @Override
    public List<Operation> getAllOperations(int page, int size) {
        PageRequest pageRequest=PageRequest.of(page,size);
        return this.operationDao.findAll(pageRequest).stream().toList();
    }
}
