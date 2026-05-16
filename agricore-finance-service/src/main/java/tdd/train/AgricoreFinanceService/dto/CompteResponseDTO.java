package tdd.train.AgricoreFinanceService.dto;

import tdd.train.AgricoreFinanceService.model.Compte;
import tdd.train.AgricoreFinanceService.model.Liquidite;

public record CompteResponseDTO (

    Integer id,
    Integer balance

) {
    public static CompteResponseDTO convert(Compte compte) {
        return new CompteResponseDTO(compte.getId(), compte.getBalance().montant());
    }

}
