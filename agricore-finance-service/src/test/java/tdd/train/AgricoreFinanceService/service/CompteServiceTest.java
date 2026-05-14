package tdd.train.AgricoreFinanceService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tdd.train.AgricoreFinanceService.exception.CompteNotFoundException;
import tdd.train.AgricoreFinanceService.exception.FondInsuffisantException;
import tdd.train.AgricoreFinanceService.exception.MontantInsuffisantException;
import tdd.train.AgricoreFinanceService.model.Compte;
import tdd.train.AgricoreFinanceService.model.Liquidite;
import tdd.train.AgricoreFinanceService.repository.CompteRepository;

@ExtendWith(MockitoExtension.class)
public class CompteServiceTest {

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
    public void should_transfer_money_between_accounts() {
        //given 
        
        Compte compteSource = new Compte(1,new Liquidite(100));
        Compte compteDestination = new Compte(2,new Liquidite(100));

        //when
        when(compteRepository.findById(1)).thenReturn(Optional.of(compteSource));
        when(compteRepository.findById(2)).thenReturn(Optional.of(compteDestination));
        compteService.virement(compteSource.getId(),compteDestination.getId(), new Liquidite(100));

        //then
        assertEquals(200 , compteDestination.getBalance().montant());
        assertEquals(0, compteSource.getBalance().montant());
    }

    @Test
    public void should_not_transfer_money_between_accounts() {
        //given 
        
        Compte compteSource = new Compte(1,new Liquidite(100));
        Compte compteDestination = new Compte(2,new Liquidite(100));

        //when
        //when(compteRepository.findById(1)).thenReturn(Optional.empty());
        //when(compteRepository.findById(2)).thenReturn(Optional.empty());
        assertThrows(CompteNotFoundException.class, () -> compteService.virement(compteSource.getId(),compteDestination.getId(), new Liquidite(100)));

        //then
        assertEquals(100 , compteDestination.getBalance().montant());
        assertEquals(100, compteSource.getBalance().montant());
    }







    @Test
    void should_return_fond_insuffisant_exception_when_montant_exceeds_balance_source() {
        Compte compteSource = new Compte(1,new Liquidite(100));
        Compte compteDestination = new Compte(2,new Liquidite(50));

        //when 
        when(compteRepository.findById(1)).thenReturn(Optional.of(compteSource));
        when(compteRepository.findById(2)).thenReturn(Optional.of(compteDestination));
        
        assertThrows(FondInsuffisantException.class, () -> compteService.virement(compteSource.getId(),compteDestination.getId(), new Liquidite(300)));

        assertEquals(100, compteSource.getBalance().montant());
        assertEquals(50, compteDestination.getBalance().montant());
        
    }

      @Test
    void should_return_montant_invalid_exception_when_montant_is_negative_from_source() {
        Compte compteSource = new Compte(1,new Liquidite(100));
        Compte compteDestination = new Compte(2,new Liquidite(50));

        //when 
        //when(compteRepository.findById(1)).thenReturn(Optional.of(compteSource));
        //when(compteRepository.findById(2)).thenReturn(Optional.of(compteDestination));
        
        assertThrows(MontantInsuffisantException.class, () -> compteService.virement(compteSource.getId(),compteDestination.getId(), new Liquidite(-300)));

        assertEquals(100, compteSource.getBalance().montant());
        assertEquals(50, compteDestination.getBalance().montant());
        
    }


}
