package tdd.train.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import tdd.train.demo.exception.EchecVirementException;
import tdd.train.demo.exception.OverBalanceException;

public class CompteTest {

    @Mock
    Compte c1;


    @Test
    void should_create_compte_with_initial_balance() {
        Compte compte = new Compte(100);
        assert compte.getBalance() == 100;
    }


    //déposer de l'argent sur le compte
    @Test 
    void should_update_balance() {
        Compte compte = new Compte(100);
        compte.deposer(150);
        assert compte.getBalance() == 250;
    }


    //retirer de l'argent du compte
    @Test
    void should_withdraw_money() {
        Compte compte = new Compte(100);
        compte.retirer(50);
        assert compte.getBalance() == 50;
    }


    //empêcher le solde de devenir négatif 
    @Test 
    void should_allow_negative_balance() {
        Compte compte = new Compte(100);
        compte.retirer(50);
        assert compte.getBalance() == 50; // le solde ne doit pas devenir négatif
    }

    @Test
    void shoud_return_exception_when_withdraw_more_than_balance() {
        Compte compte = new Compte(100);
        
        assertThrows(OverBalanceException.class, () -> compte.retirer(150));
        assertEquals(100, compte.getBalance()); 
        
    }

    @Test
    void should_make_transaction_to_other_account() {

        Compte c1 = new Compte(100);
        Compte c2 = new Compte(50);

        c1.virement(c2, 30);

        assertEquals(70, c1.getBalance());
        assertEquals(80, c2.getBalance());

    }

    @Test
    void should_not_allow_transaction_to_other_account() {
        Compte c1 = new Compte(100);
        Compte c2 = new Compte(50);

        
         assertThrows(EchecVirementException.class,
        () -> c1.virement(c2, 300));
        assertEquals(100, c1.getBalance());
        assertEquals(50, c2.getBalance());
        

        
    }









}
