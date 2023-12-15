package com.bookmanagement.gestionlivres.service;


import com.bookmanagement.gestionlivres.model.Compte;

import java.util.List;
import java.util.Optional;

public interface CompteService {
    Compte addAcound(Compte compte);
    void deleteAccount(String id);
    List<Compte>getComptes();
    Compte findCompteById(String id);
    List<Compte> findCompteByUserId(String userId);
    void updateAcount(Compte compte,String id);
}
