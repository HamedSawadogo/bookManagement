package com.bankmanageemnt.banktp.services;

import com.bankmanageemnt.banktp.controllers.CompteController;
import com.bankmanageemnt.banktp.dao.CompteEpargneDao;
import com.bankmanageemnt.banktp.model.CompteEpargne;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CompteEpargneImpl implements CompteService<CompteEpargne> {

    private final CompteEpargneDao compteEpargneDao;

    public CompteEpargneImpl(CompteEpargneDao compteEpargneDao) {
        this.compteEpargneDao = compteEpargneDao;
    }

    @Override
    public CompteEpargne addAcount(CompteEpargne compteEpargne) {
        return this.compteEpargneDao.save(compteEpargne);
    }

    @Override
    public CompteEpargne findCompteById(String numCompte) {
        return this.compteEpargneDao.getReferenceById(numCompte);
    }
}
