package com.bankmanageemnt.banktp.controllers;
import com.bankmanageemnt.banktp.dto.CreateClientRequest;
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

    /**
     * Enregistrer un client
     * @param client
     * @return
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Client addClient(@RequestBody Client client){
        return this.clientService.addClient(client);
    }
    /**
     * Attribuer Un compte a Un client
     * @param clientId
     * @param accountId
     * @return
     */
    @PostMapping("/{clientId}/{accountId}")
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
    /**
     * Modifier Un client
     * @param id
     * @param client
     * @return
     */
    @PutMapping("/{id}")
    public  Client updateClient(@PathVariable("id")String id,@RequestBody Client client){
       return   this.clientService.updateClient(client,id);
    }
    /**
     * Supprimer un CLient
     * @param clientId
     */
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable("id")String clientId){
        this.clientService.deleteClientById(clientId);
    }
    /**
     * Renvoyer un client Par Son Id
     * @param clientId
     * @return
     */
    @GetMapping("/{id}")
    public Client findClientById(@PathVariable("id")String clientId){
        return this.clientService.findClientById(clientId);
    }
    @GetMapping("/name/{name}")
    public List<Client>findClientsByName(@PathVariable("name")String name)
    {
        return this.clientService.findClientsByName(name);
    }
    @GetMapping("")
    public Page<Client>findAll(
            @RequestParam(defaultValue ="0")int page,
            @RequestParam(defaultValue ="10")int size
    ){
        return this.clientService.findAll(page,size);
    }
}


