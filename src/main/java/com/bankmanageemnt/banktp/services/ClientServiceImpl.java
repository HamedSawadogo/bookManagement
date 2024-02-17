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
    /**
     * Ajouter un Client
     * @param client
     * @return
     */
    @Override
    public Client addClient(Client client) {
        if(client==null){
            throw new EntityNotFoundException("this client is not a valid client");
        }
        client.setCode(UUID.randomUUID().toString());
        return this.clientDao.save(client);
    }
    /**
     * Rechercher un Client Par Son Nom
     * @param clientName
     * @return
     */
    @Override
    public List<Client> findClientsByName(String clientName) {
        return this.clientDao.findClientsByName(clientName);
    }
    /**
     * Renvoie la liste des Pages Clients
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Client> findAll(int page,int size) {
        PageRequest pageRequest=PageRequest.of(page,size);
        return this.clientDao.findAll(pageRequest).stream().toList();
    }
    /**
     * Attribuer un Compte a Un Client
     * @param clientId
     * @param accountId
     * @return
     */
    @Override
    public Client addAcountToClient(String clientId, String accountId) {
        Client client=this.clientDao.findById(clientId)
                .orElseThrow(()->new EntityNotFoundException("this client does not exists"));

        Compte compte=this.compteDao.findById(accountId)
                .orElseThrow(()->new EntityNotFoundException("this account does not exists"));
        //
        client.getComptes().add(compte);
        compte.setClient(client);
        return  this.clientDao.save(client);
    }
    /**
     * Rechercher Un client Par Son Id
     * @param clientId
     * @return
     */
    @Override
    public Client findClientById(String clientId) {
        return this.clientDao.findById(clientId)
                .orElseThrow(()->new EntityNotFoundException("this client is not Found"));
    }
    /**
     * Supprimer un client
     * @param clientId
     */
    @Override
    public void deleteClientById(String clientId) {
      Client client=this.findClientById(clientId);
      this.clientDao.deleteById(client.getCode());
    }
    /**
     * rechercher un CLient par son Nom
     * @param clientName
     * @return
     */
    @Override
    public Client findCLientByName(String clientName) {
        return this.clientDao.findClientByName(clientName);
    }
    /**
     * Mettre a Jour Un client
     * @param client
     * @param clientId
     * @return
     */
    @Override
    public Client updateClient(Client client, String clientId) {
        Client client1=this.findClientById(clientId);
        Client newClient=Client.builder()
                .code(client1.getCode())
                .name(client.getName())
                .build();
        return this.addClient(newClient);
    }
}
