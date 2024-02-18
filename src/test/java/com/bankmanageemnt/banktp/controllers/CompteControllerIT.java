package com.bankmanageemnt.banktp.controllers;

import com.bankmanageemnt.banktp.dao.CompteEpargneDao;
import com.bankmanageemnt.banktp.services.CompteEpargneImpl;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.is;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Sawadogo hamed   email <kerb418@gmail.com>
 **/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@Tag("Comptes")
@DisplayName("Tester les Opérations sur les Comptes")
public class CompteControllerIT {

    @Autowired
    private CompteEpargneImpl compteEpargne;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompteEpargneDao compteEpargneDao;
    private JSONObject json;

    @BeforeAll
    @AfterAll
    public void clearDatabase(){
        this.compteEpargneDao.deleteAll();
        this.json=null;
    }

    @Order(value = 1)
    @DisplayName("Tester la création d'un Compte Courant")
    @Test
    public void testCreateCurrentAccount() throws Exception{
        MvcResult result=this.mockMvc.perform(MockMvcRequestBuilders.post("/comptes/courant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"decouvert\": 0,\n" +
                        "  \"numCompte\": \"\",\n" +
                        "  \"solde\": 100,\n" +
                        "  \"creationDate\": \"2024-02-14T15:18:36.000+00:00\"\n"
                        +
                        "}")
                 )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.decouvert", is(0d)))
                .andExpect(jsonPath("$.solde", is(100d)))
                .andExpect(jsonPath("$.creationDate", is("2024-02-14T15:18:36.000+00:00")))
                .andReturn();
        this.json=new JSONObject(result.getResponse().getContentAsString());
    }

}
