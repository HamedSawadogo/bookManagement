package com.bankmanageemnt.banktp.services;
import com.bankmanageemnt.banktp.model.Client;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {
    Client addClient(Client client);
    List<Client> findClientsByName(String clientName);
    List<Client> findAll(int page,int size);
    Client addAcountToClient(String clientId,String accountId);
    Client findClientById(String clientId);
    void deleteClientById(String clientId);
    Client findCLientByName(String clientName);
    Client updateClient(Client client,String clientId);
}
