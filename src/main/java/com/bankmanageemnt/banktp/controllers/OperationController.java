package com.bankmanageemnt.banktp.controllers;
import com.bankmanageemnt.banktp.model.Operation;
import com.bankmanageemnt.banktp.services.OperationServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/operations")
@RestController
public class OperationController {

    private final OperationServiceImpl operationService;

    public OperationController(OperationServiceImpl operationService) {
        this.operationService = operationService;
    }
    /**
     * Renvoie la liste des Opérations
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/all")
    public List<Operation>findAll(
        @RequestParam(defaultValue = "0")int page,
        @RequestParam(defaultValue = "10")int size
    ){
        return this.operationService.getAllOperations(page,size);
    }
    /**
     * Transferer de l'argent Dans un Autre Compte
     * @param sender
     * @param receiver
     * @param amount
     * @return
     */
    @PostMapping("/transfert/{sender}/{receiver}/{amount}")
    public ResponseEntity<String> transferer(
            @PathVariable("sender")String sender,
            @PathVariable("receiver")String receiver,
            @PathVariable("amount")Double amount
    ){
        try {
            this.operationService.transferer(amount,sender,receiver);
            return ResponseEntity.status(HttpStatus.OK).body("transfert effectué avec success!!  ");
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    /**
     * Faire un dépot
     * @param account
     * @param montant
     */
    @PostMapping("/depot/{id}/{montant}")
    public ResponseEntity<String> deposer(@PathVariable("id")String account, @PathVariable("montant")Double montant){
       try {
           this.operationService.deposer(montant,account);
           return ResponseEntity.status(HttpStatus.OK).body("Retrait effectué avec success!!  ");
       }catch (EntityNotFoundException e) {
           return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
       }
    }
    /**
     * retirer de l'argent
     * @param account
     * @param montant
     * @return
     */
    @PostMapping("/retrait/{id}/{montant}")
    public ResponseEntity<?> retrait(@PathVariable("id")String account, @PathVariable("montant")Double montant){
        try {
            this.operationService.retirer(montant,account);
            return ResponseEntity.status(HttpStatus.CREATED).body("retrait effectué avec success!!");
        } catch (NoSuchFieldException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
