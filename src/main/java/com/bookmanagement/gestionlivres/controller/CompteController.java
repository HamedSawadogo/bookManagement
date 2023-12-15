package com.bookmanagement.gestionlivres.controller;

import com.bookmanagement.gestionlivres.exceptions.UserNotFoundException;
import com.bookmanagement.gestionlivres.model.Compte;
import com.bookmanagement.gestionlivres.model.User;
import com.bookmanagement.gestionlivres.service.CompteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompteController {

    private final CompteServiceImpl compteService;

    CompteController(CompteServiceImpl compteService){
        this.compteService=compteService;
    }

    @RequestMapping(value = "/comptes",method = RequestMethod.GET)
    public List<Compte>comptes(){
        return this.compteService.getComptes();
    }

    @RequestMapping(value = "/{userId}/comptes",method = RequestMethod.GET)
    public  List<Compte>getUserAcounts(@PathVariable("userId")String userId){
        return  this.compteService.findCompteByUserId(userId);
    }

    @RequestMapping(value = "/compte",method = RequestMethod.POST)
    public  ResponseEntity<Compte> createCompte(@RequestBody Compte compte){
        return  ResponseEntity.status(HttpStatus.CREATED).body(this.compteService.addAcound(compte));
    }

    @RequestMapping(value = "/{userId}/{compteId}",method = RequestMethod.POST)
    public ResponseEntity<String> addAcountToUser(@PathVariable("userId")String userId, @PathVariable("compteId")String compteId){
        try {
            this.compteService.addAcountToUsser(userId,compteId);
            return ResponseEntity.ok().body("Compte ajouté avec succes");
        } catch (UserNotFoundException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
