package tdd.train.AgricoreFinanceService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;
import tdd.train.AgricoreFinanceService.model.Compte;
import tdd.train.AgricoreFinanceService.model.Liquidite;
import tdd.train.AgricoreFinanceService.repository.CompteRepository;


@AutoConfigureMockMvc
@SpringBootTest
public class CompteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private CompteRepository repository;


    @BeforeEach
    void setUp() {
        repository.deleteAll();
        repository.save(new Compte(new Liquidite(1000)));
        repository.save(new Compte(new Liquidite(500)));
    } // gestion dynamique des id  car ordre d'insertion non garanti ( générer automatiquement par la base de données et pas forcément dans le meme ordres)
      // afin des garantir des tests reproductibles

     

    @Test 
    @Transactional // dans les tests , transactional permet de rollback même quand le test est passé , pour éviter les effets de bord entre les tests et garantir l'isolation des tests
    public void testVirement() throws Exception {

        Compte c1 = repository.findAll().get(0);
        Compte c2 = repository.findAll().get(1);

        mockMvc.perform(post("/api/comptes/virement")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
            {
                "sourceId": %d,
                "destinationId": %d,
                "montant": 100
            }
            """.formatted(c1.getId(), c2.getId())))
        .andExpect(status().isOk());

    Compte source = repository.findById(c1.getId()).get();
    Compte dest = repository.findById(c2.getId()).get();

    assertEquals(new Liquidite(900), source.getBalance());
    assertEquals(new Liquidite(600), dest.getBalance());
    
    }

}
