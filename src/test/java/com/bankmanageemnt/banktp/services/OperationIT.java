package com.bankmanageemnt.banktp.services;
import com.bankmanageemnt.banktp.dao.OperationDao;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Sawadogo hamed   email <kerb418@gmail.com>
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@Tag("Opérations")
@DisplayName("Tester le crud des Opérations de Compte Epargne")
public class OperationIT {

    private JSONObject jsonObject;
    @Autowired
    private OperationDao operationDao;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    @AfterAll
    public void initDatabse(){
        this.operationDao.deleteAll();
        this.jsonObject=null;
    }

    @Test
    @Order(value = 1)
    @DisplayName("tester la création des Comptes")
    public void testCreateSavingAcount() throws Exception{
        MvcResult result=this.mockMvc.perform(post("/comptes/epargne")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "  \"taux\": 0,\n" +
                            "  \"solde\": 0,\n" +
                            "  \"creationDate\": \"2024-02-14T15:18:36.000+00:00\"\n" +
                            "}")
            ).andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.taux",is(0.)))
                .andReturn();
        jsonObject=new JSONObject(result.getResponse().getContentAsString());
    }
    @Test
    @Order(value = 2)
    @DisplayName("Deposer 100 € dans ce compte")
    public void testDisposeMoneyShouldReturn100€() throws Exception{
        MvcResult result=this.mockMvc.perform(post("/operations/depot/"+ jsonObject.get("numCompte")+"/"+100)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.solde",is(100.)))
                .andReturn();
    }
}
