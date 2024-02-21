package com.bankmanageemnt.banktp.services;
import com.bankmanageemnt.banktp.dao.ClientDao;
import com.bankmanageemnt.banktp.dao.CompteDao;
import com.bankmanageemnt.banktp.model.Client;
import com.bankmanageemnt.banktp.model.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit Test
 */
@Tag("Tester les Opérations Sur le CLient")
@DisplayName("Tester toutes les Opérations éffectués par le Client")
@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    ClientDao clientDao;
    @Mock
    CompteDao compteDao;
    @Mock
    BCryptPasswordEncoder passwordEncoder;

    ClientServiceImpl clientService;

    @BeforeEach
    public void setup(){
      this.clientService=new ClientServiceImpl(clientDao,compteDao,passwordEncoder);
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

}
