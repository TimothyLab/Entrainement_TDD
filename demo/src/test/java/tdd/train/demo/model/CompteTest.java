package tdd.train.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tdd.train.demo.exception.FondInsuffisantException;
import tdd.train.demo.repository.CompteRepository;
import tdd.train.demo.service.CompteService;

@ExtendWith(MockitoExtension.class)
public class CompteTest {

    @Mock
    Compte c1;

    @Mock
    private CompteRepository compteRepository;

    @Mock 
    Liquidite montant;

    @Mock
    private Compte compteSource;

    @Mock 
    private Compte compteDestination;

    @InjectMocks
    private CompteService compteService;


    @Test
    void should_create_compte_with_initial_balance() {
        Compte compte = new Compte(1, new Liquidite(100));
        assert compte.getBalance().montant() == new Liquidite(100).montant();
    }


    //déposer de l'argent sur le compte
    @Test 
    void should_update_balance() {
        Compte compte = new Compte(1, new Liquidite(100));
        compte.deposer(new Liquidite(100));
        assert compte.getBalance().montant() == new Liquidite(200).montant();
    }


    //retirer de l'argent du compte
    @Test
    void should_withdraw_money() {
        Compte compte = new Compte(1, new Liquidite(100));
        compte.retirer(new Liquidite(100));
        assert compte.getBalance().montant() == new Liquidite(0).montant();
    }

    @Test
    void shoud_return_exception_when_withdraw_more_than_balance() {
        Compte compte = new Compte(1, new Liquidite(100));
        
        assertThrows(FondInsuffisantException.class, () -> compte.retirer(new Liquidite(150)));
        assertEquals(100, compte.getBalance().montant()); 
        
    }

  

    









}
