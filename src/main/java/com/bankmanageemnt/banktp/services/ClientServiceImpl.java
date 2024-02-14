package com.bankmanageemnt.banktp.services;
import com.bankmanageemnt.banktp.dao.ClientDao;
import com.bankmanageemnt.banktp.model.Client;
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

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public Client addClient(Client client) {
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
}
