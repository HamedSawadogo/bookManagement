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
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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
    ){
        this.operationDao = operationDao;
        this.compteDao = compteDao;
        this.compteEpargneDao = compteEpargneDao;
        this.compteCourantDao = compteCourantDao;
    }
    /**
     * Déposer de l'argent sur Un compte
     * @param amount
     * @param accountId
     */
    @Override
    public void deposer(Double amount, String accountId) {
        Compte compte=this.compteDao.findCompteByNumCompte(accountId);
        compte.setSolde(compte.getSolde()+amount);

        Operation operation=this.createOperation(
                new Date(),
                OperationType.VERSEMENT,
                amount
        );
        compte.getOperations().add(operation);
        operation.setCompte(compte);
        this.operationDao.save(operation);
    }
    /**
     * Retirer de l'argent
     * @param montant
     * @param accountId
     * @throws NoSuchFieldException
     */
    @Override
    public void retirer(Double montant, String accountId) throws NoSuchFieldException {

        if(montant<0)throw new EntityNotFoundException("le montant est incorect!!  ");
        Compte compte=this.compteDao.findCompteByNumCompte(accountId);
        if(compte==null)throw new NoSuchElementException("this account not Found");
        //
        if(compte.getSolde()<montant){
            if(compte instanceof CompteCourant){
                CompteCourant compteCourant=(CompteCourant) compte;
                Double decouvert=montant-compte.getSolde();
                compteCourant.setDecouvert(compteCourant.getDecouvert()+decouvert);
            }
        }
        Operation operation=this.createOperation(
                new Date(),
                OperationType.RETRAIT,
                montant
        );
        compte.setSolde(compte.getSolde()-montant);
        compte.getOperations().add(operation);
        operation.setCompte(compte);
        this.operationDao.save(operation);
    }
    /**
     * Creation Un opération
     * @param date
     * @param type
     * @param amount
     * @return
     */
    private Operation createOperation(Date date,OperationType type,Double amount){
        Operation operation =Operation.builder()
            .operationDate(date)
            .operationType(type)
            .montant(amount)
            .build();
       return  operation;
    }
    /**
     * Transferer d'un compte a Un Autre compte
     * @param montant
     * @param accountA
     * @param accountB
     */
    @Override
    public void transferer(Double montant, String sender, String receiver) {
        if(montant<0)throw new EntityNotFoundException("this amount is not valide");
        Compte senderAcount=this.compteDao.findCompteByNumCompte(sender);
        Compte receiverAcount=this.compteDao.findCompteByNumCompte(receiver);
        if(senderAcount==null||receiverAcount==null){
            throw new EntityNotFoundException("Une erreur est survenu lors du transfert");
        }
        if(senderAcount.getSolde()<montant){throw new EntityNotFoundException("votre solde est insuffisant!! ");}

        Operation operation=this.createOperation(new Date(),OperationType.TRANSFERT,montant);
        senderAcount.getOperations().add(operation);
        operation.setCompte(senderAcount);

        receiverAcount.setSolde(receiverAcount.getSolde()+montant);
        senderAcount.setSolde(senderAcount.getSolde()-montant);
        this.operationDao.save(operation);
    }
    /**
     * Afficher la liste des opérations page par page
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Operation> getAllOperations(int page, int size) {
        PageRequest pageRequest=PageRequest.of(page,size);
        return this.operationDao.findAll(pageRequest).stream().toList();
    }

}
