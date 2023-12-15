package com.bookmanagement.gestionlivres.service;

import com.bookmanagement.gestionlivres.Dtos.UserDto;
import com.bookmanagement.gestionlivres.exceptions.UserNotFoundException;
import com.bookmanagement.gestionlivres.model.Compte;
import com.bookmanagement.gestionlivres.model.CompteVisa;
import com.bookmanagement.gestionlivres.model.ComteOrangeMoney;
import com.bookmanagement.gestionlivres.model.User;
import com.bookmanagement.gestionlivres.repositories.CompteDao;
import com.bookmanagement.gestionlivres.repositories.UserDao;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Transactional
@Service
public class CompteServiceImpl implements CompteService {

    private final UserDao userService;
    private final CompteDao compteDao;

    public  CompteServiceImpl(CompteDao compteDao,UserDao userService){
        this.compteDao=compteDao;
        this.userService=userService;;
    }
    public void addAcountToUsser(String userId,String countId) throws UserNotFoundException {
        Optional<Compte> compte = compteDao.findById(countId);
        Optional<User> user = userService.findById(userId);

        if(user.isPresent()&&compte.isPresent()){
            Compte compte1=compte.get();
            User user1=user.get();
            user1.getComptes().add(compte1);
            compte1.setUser(user1);
        }
    }

    @Override
    public Compte addAcound(Compte compte) {
        if(compte instanceof ComteOrangeMoney){
            System.err.println("Compte Orange Money");
            return  null;
        } else if (compte instanceof  CompteVisa) {
            System.err.println("Compte Visa");
            return null;
        }

        if(Objects.isNull(compte))throw new NoSuchElementException("Ce compte est invalide! ");
        compte.setId(UUID.randomUUID().toString());
        return compteDao.save(compte);
    }

    @Override
    public void deleteAccount(String id) {
      compteDao.deleteById(id);
    }

    @Override
    public List<Compte> getComptes() {
        return compteDao.findAll();
    }

    @Override
    public Compte findCompteById(String id) {
        return compteDao.findById(id).orElseThrow(()->new NoSuchElementException("ce Compte est introuvable"));
    }

    @Override
    public List<Compte> findCompteByUserId(String userId) {
        return compteDao.findComptesByUserId(userId);
    }

    @Override
    public void updateAcount(Compte compte, String id) {
        Compte newCompte;
        if(compte instanceof ComteOrangeMoney)
            newCompte=new ComteOrangeMoney();
        else
            newCompte=new CompteVisa();

       // modelMapper.map(newCompte,Compte.class);
        System.err.println(newCompte.getMontant());
        System.err.println(newCompte.getDateCreation());
    }
}
