package com.bankmanageemnt.banktp.controllers;
import com.bankmanageemnt.banktp.model.Client;
import com.bankmanageemnt.banktp.services.ClientServiceImpl;
import org.springframework.data.domain.Page;
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


