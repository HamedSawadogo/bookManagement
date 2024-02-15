package com.bankmanageemnt.banktp.services;
import com.bankmanageemnt.banktp.dao.ClientDao;
import com.bankmanageemnt.banktp.dao.CompteDao;
import com.bankmanageemnt.banktp.model.Client;
import com.bankmanageemnt.banktp.model.Compte;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;
    private final CompteDao compteDao;

    public ClientServiceImpl(ClientDao clientDao, CompteDao compteDao) {
        this.clientDao = clientDao;
        this.compteDao = compteDao;
    }

    @Override
    public Client addClient(Client client) {
        if(client==null){
            throw new EntityNotFoundException("this client is not a valid client");
        }
        client.setCode(UUID.randomUUID().toString());
        return this.clientDao.save(client);
    }
    @Override
    public List<Client> findClientsByName(String clientName) {
        return this.clientDao.findClientsByName(clientName);
    }

    @Override
    public List<Client> findAll(int page,int size) {
        PageRequest pageRequest=PageRequest.of(page,size);
        return this.clientDao.findAll(pageRequest).stream().toList();
    }

    @Override
    public Client addAcountToClient(String clientId, String accountId) {
        Client client=this.clientDao.findById(clientId)
                .orElseThrow(()->new EntityNotFoundException("this client does not exists"));

        Compte compte=this.compteDao.findById(accountId)
                .orElseThrow(()->new EntityNotFoundException("this account does not exists"));

        System.err.println(compte);

        //assign a Client to a Account vis Versa
        client.getComptes().add(compte);
        compte.setClient(client);

        return  this.clientDao.save(client);
    }
}
