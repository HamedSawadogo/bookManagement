package com.bankmanageemnt.banktp.controllers;
import com.bankmanageemnt.banktp.model.Client;
import com.bankmanageemnt.banktp.services.ClientServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/clients")
@RestController
public class ClientController{

    private final ClientServiceImpl clientService;
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/client")
    public Client addClient(@RequestBody Client client){
        return this.clientService.addClient(client);
    }

    @PostMapping("/add/{clientId}/{accountId}")
    public ResponseEntity addAcountToClient(
            @PathVariable("clientId") String clientId,
            @PathVariable("accountId") String accountId)
    {try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    this.clientService.addAcountToClient(clientId,accountId)
            );
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/name/{name}")
    public List<Client>findClientsByName(@PathVariable("name")String name)
    {
        return this.clientService.findClientsByName(name);
    }
    @GetMapping("/all")
    public List<Client>findAll(
            @RequestParam(defaultValue ="0")int page,
            @RequestParam(defaultValue ="10")int size
    ){
        return this.clientService.findAll(page,size);
    }
}


