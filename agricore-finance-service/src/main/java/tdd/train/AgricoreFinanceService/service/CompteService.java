package tdd.train.AgricoreFinanceService.service;

import org.springframework.stereotype.Service;

import tdd.train.AgricoreFinanceService.exception.CompteNotFoundException;
import tdd.train.AgricoreFinanceService.model.Compte;
import tdd.train.AgricoreFinanceService.model.Liquidite;
import tdd.train.AgricoreFinanceService.repository.CompteRepository;

@Service
public class CompteService {

    private final CompteRepository compteRepository;

    public CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    public void virement(Integer sourceId, Integer destinationId, Liquidite montant) {

        Compte source = compteRepository.findByUserId(sourceId);
        Compte destination = compteRepository.findByUserId(destinationId);

        source.retirer(montant);
        destination.deposer(montant);

        compteRepository.save(source);
        compteRepository.save(destination);

    }


    
}
