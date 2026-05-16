package tdd.train.AgricoreFinanceService.dto;

import tdd.train.AgricoreFinanceService.model.Compte;

public record CompteResponseDTO (

    Integer balance,
    Integer userId 


) {
    public static CompteResponseDTO convert(Compte compte) {
        return new CompteResponseDTO(compte.getBalance().montant(), compte.getUserId());
    }

}
