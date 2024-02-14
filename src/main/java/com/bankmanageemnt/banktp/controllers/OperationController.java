package com.bankmanageemnt.banktp.controllers;
import com.bankmanageemnt.banktp.model.Operation;
import com.bankmanageemnt.banktp.services.OperationServiceImpl;
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

    @GetMapping("/all")
    public List<Operation>findAll(
        @RequestParam(defaultValue = "0")int page,
        @RequestParam(defaultValue = "10")int size
    ){
        return this.operationService.getAllOperations(page,size);
    }

    @PostMapping("/depot/{id}/{montant}")
    public void deposer(@PathVariable("id")String account, @PathVariable("montant")Double montant){
         this.operationService.deposer(montant,account);
    }

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
