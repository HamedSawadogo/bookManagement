package com.bankmanageemnt.banktp.controllers;
import com.bankmanageemnt.banktp.dao.ClientDao;
import net.minidev.json.JSONUtil;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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
@Tag("Clients")
@DisplayName("Test des clients")
public class ClientControllerTest {

    @Autowired
    private ClientDao clientDao;
    @Autowired
    private MockMvc mockMvc;


    private JSONObject json;

    @BeforeAll
    @AfterAll
    public void clearDatabase(){
        clientDao.deleteAll();
        this.json=null;
    }
    @Test
    @Order(value = 1)
    @DisplayName("Tester l'ajout d'un nouveau Client")
    public void testAddNewClient() throws Exception{
        MvcResult result=mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("   {\n" +
                                "        \"name\": \"Kader\"\n" +
                                "     \n" +
                                "        }")
                ).andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.name",is("Kader")))
                .andReturn();

        this.json=new JSONObject(result.getResponse().getContentAsString());
    }

    @Test
    @Order(value = 2)
    @DisplayName("rechercher un Client par Son ID")
    public  void testGetClientById() throws  Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get(
                "/clients/"+this.json.getString("code"))
                .contentType(MediaType.APPLICATION_JSON)
                 ).andDo(print())
                .andExpect(jsonPath("$.name",is("Kader")));
    }
    @Test
    @Order(value = 3)
    @DisplayName("Renvoie la liste des Clients")
    public void testGetClientsList() throws Exception{
        this.mockMvc.perform(get("/clients")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].name",is("Kader")));
    }


    @Test
    @Order(value = 5)
    @DisplayName("Modifier Un client Un client Par Son Id")
    public  void testUpdateClient() throws  Exception{
        this.mockMvc.perform(
                         put("/clients/"+json.getString("code")
                        ).contentType(MediaType.APPLICATION_JSON)
                                 .content("  {\n" +
                                         "     \"name\":\"Valangui\"\n" +
                                         "   }")
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("Valangui")));

    }
    @Test
    @Order(value = 5)
    @DisplayName("Supprimer Un client Par Son Id")
    public  void testDeleteClient() throws  Exception{
       this.mockMvc.perform(delete("/clients/"+json.getString("code")
               )
       ).andDo(print())
               .andExpect(status().isOk());


    }




}
