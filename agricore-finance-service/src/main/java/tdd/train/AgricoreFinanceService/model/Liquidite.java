package tdd.train.AgricoreFinanceService.model;

import jakarta.persistence.Embeddable;
import tdd.train.AgricoreFinanceService.exception.FondInsuffisantException;
import tdd.train.AgricoreFinanceService.exception.MontantInsuffisantException;

@Embeddable
public record Liquidite(int montant) {

    public Liquidite {
        if (montant < 0) {
            throw new MontantInsuffisantException("Montant négatif invalide");
        }
    }

    public Liquidite ajouter (Liquidite autre) {
        return new Liquidite(this.montant + autre.montant);
    }

    public Liquidite retirer(Liquidite autre) {
        if (this.montant < autre.montant) {
            throw new FondInsuffisantException("Fond insuffisant");
        }
        return new Liquidite(this.montant - autre.montant);
    }



}
