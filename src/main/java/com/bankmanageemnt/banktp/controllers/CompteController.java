package com.bankmanageemnt.banktp.controllers;
import com.bankmanageemnt.banktp.dao.CompteCourantDao;
import com.bankmanageemnt.banktp.dao.CompteDao;
import com.bankmanageemnt.banktp.dao.CompteEpargneDao;
import com.bankmanageemnt.banktp.model.Compte;
import com.bankmanageemnt.banktp.model.CompteCourant;
import com.bankmanageemnt.banktp.model.CompteEpargne;
import com.bankmanageemnt.banktp.services.CompteEpargneImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@RequestMapping("/comptes")
@RestController
public class CompteController {

    private final CompteEpargneDao compteEpargneService;
    private final CompteCourantDao compteCourantDao;
    private final CompteDao compteDao;

    public CompteController(CompteEpargneDao compteEpargneService, CompteCourantDao compteCourantDao, CompteDao compteDao) {
        this.compteEpargneService = compteEpargneService;
        this.compteCourantDao = compteCourantDao;
        this.compteDao = compteDao;
    }

    @GetMapping("/all")
    public List<Compte>findAll(){
        this.compteDao.findAll()
                .forEach(compte -> {
                    if(compte instanceof CompteCourant){
                        log.info("Compte Courant");
                        log.info(compte.toString());
                    }
                    if(compte instanceof  CompteEpargne){
                        log.info("Compte d'épargne");
                        log.info(compte.toString());
                    }
                });
        return this.compteDao.findAll();
    }

    @PostMapping("/add")
    public Compte addCompte(@RequestBody Compte compte){
        System.err.println("Compte:  "+compte.toString());
        compte.setNumCompte(UUID.randomUUID().toString());
        if(compte instanceof CompteEpargne){
            CompteEpargne compteEpargne=(CompteEpargne) compte;
            log.error("Enregistrement d'un Compte d'épargne "+compteEpargne.toString());
            return this.compteEpargneService.save(compteEpargne);
        }else if(compte instanceof CompteCourant){
            CompteCourant compteCourant=(CompteCourant)compte;
            log.error("Enregistrement d'un Compte Courant "+compteCourant.toString());
            return this.compteCourantDao.save(compteCourant);
        }
        return  null;
    }
    @GetMapping("/{id}")
    public Compte findAcoundById(@PathVariable("id") String countNumber){
        return this.compteDao.findById(countNumber).orElseThrow(()->new EntityNotFoundException("this acount is not Found"));
    }
}
