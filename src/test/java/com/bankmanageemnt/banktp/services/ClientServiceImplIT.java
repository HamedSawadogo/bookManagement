package com.bankmanageemnt.banktp.services;
import com.bankmanageemnt.banktp.model.Client;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Intégrations Test
 * @author hamed
 * @version 1.1
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientServiceImplIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ClientServiceImpl clientService;

    @Test
    @Order(1)
    @DisplayName("Enregistrer un client")
    public void addClient(){
        Client client=Client.builder()
                .code(UUID.randomUUID().toString())
                .name("Akim")
                .build();
        Client clientSaved=this.clientService.addClient(client);
        assertThat(clientSaved).isEqualTo(client);
    }
    @Test
    @Order(2)
    @DisplayName("Rechercher un client Par Nom")
    public void searchClientByName(){
        Client client1=this.clientService.findCLientByName("Akim");
        assertThat(client1.getName()).isEqualTo("Akim");

    }
    @Test
    @Order(3)
    @DisplayName("Modifier un Client ")
    public void updateClient(){

        Client newClient=Client.builder()
                .code(UUID.randomUUID().toString())
                .name("Bill")
                .build();

        Client client1=this.clientService.findCLientByName("Akim");
        Client clientUpdated=this.clientService.updateClient(newClient,client1.getCode());
        assertThat(clientUpdated.getName()).isEqualTo("Bill");
    }

    @Test
    @Order(4)
    @DisplayName("Supprimer un Client ")
    public void deleteClientById(){

        Client client1=this.clientService.findCLientByName("Akim");
        this.clientService.deleteClientById(client1.getCode());

        assertThrows(EntityNotFoundException.class,() -> {
            Client client=this.clientService.findClientById(client1.getCode());
        });
    }


}
