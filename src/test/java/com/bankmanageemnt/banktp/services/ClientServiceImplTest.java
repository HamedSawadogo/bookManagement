package com.bankmanageemnt.banktp.services;
import com.bankmanageemnt.banktp.dao.ClientDao;
import com.bankmanageemnt.banktp.dao.CompteDao;
import com.bankmanageemnt.banktp.model.Client;
import com.bankmanageemnt.banktp.model.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit Test
 */
@Tag("ClientServiceImpl")
@DisplayName("Tester le service des CLiens => ClientServiceImpl")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(controllers ={ClientServiceImpl.class})
public class ClientServiceImplTest {

    @MockBean
    ClientDao clientDao;
    @MockBean
    CompteDao compteDao;

    ClientServiceImpl clientService;


    @BeforeEach
    public void setup(){
      this.clientService=new ClientServiceImpl(clientDao,compteDao);
    }

    @BeforeAll
    @AfterAll
    public void clearDatabase(){
        this.clientDao.deleteAll();
        this.clientService=null;
    }
    //EntityNotFoundException

    @Test
    @DisplayName("Tester l'Ajout d'un Client invalide")
    public void testAddInvalidClient(){
        Client client=null;
        assertThrows(EntityNotFoundException.class,() -> {
            Client client1=this.clientService.addClient(client);
        },"this client is not a valid client");
    }
    @Test
    @DisplayName("Tester l'Ajout d'un Client")
    public void testAddNewClient(){
        //GIVEN
        Client client=Client.builder()
                .code(UUID.randomUUID().toString())
                .name("Paul")
                .build();
        //WHEN
        when(this.clientDao.save(client)).thenReturn(client);
        Client client1=this.clientService.addClient(client);
        //THEN
        verify(this.clientDao).save(client);
        assertThat(client1).isEqualTo(client1);
    }

    @Test
    @DisplayName("Tester la recherche d'un Client")
    public void testFindCLientByName(){
        //GIVEN
        Client client=Client.builder()
                .code(UUID.randomUUID().toString())
                .name("Paul")
                .build();
        client=this.clientDao.save(client);
        //WHEN
        when(this.clientDao.findClientByName("Paull")).thenReturn(client);
        Client clientFinded=this.clientService.findCLientByName("Paul");

        //THEN
        verify(this.clientDao).findClientByName("Paul");
        assertThat(clientFinded).isEqualTo(client);
    }

}
