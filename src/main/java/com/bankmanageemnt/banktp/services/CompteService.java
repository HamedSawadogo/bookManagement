package com.bankmanageemnt.banktp.services;

import com.bankmanageemnt.banktp.model.Compte;

public interface CompteService<Compte> {
    Compte addAcount(Compte compte);
    Compte findCompteById(String numCompte);
}
