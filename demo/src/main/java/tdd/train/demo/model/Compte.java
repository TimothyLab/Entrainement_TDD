package tdd.train.demo.model;

import tdd.train.demo.exception.EchecVirementException;
import tdd.train.demo.exception.OverBalanceException;

public class Compte {

    private Liquidite balance;

    public Compte( Liquidite balance) {
        this.balance = balance;
    }

    public Liquidite getBalance() {
        return balance;
    }

    

    //méthode pour déposer de l'argent sur le compte
    public void deposer(Liquidite montant) {
        verifierMontantValide(montant);
        this.balance = balance.ajouter(montant);
    }


    //méthode pour retirer de l'argent du compte
    public void retirer(Liquidite montant) {
        verifierMontantValide(montant);
        verifierSolvabilite(montant);
        this.balance = balance.retirer(montant);
    
    }

    public void virement(Compte compteDestinataire, Liquidite montant) {
        this.retirer(montant);
        compteDestinataire.deposer(montant);
        
    }

    public void verifierSolvabilite(Liquidite montant) {
        if (montant.montant() >this.balance.montant()) {
            throw new OverBalanceException("Le solde ne peut pas devenir négatif");
        }
    }

    private void verifierMontantValide(Liquidite montant) {
        if (montant.montant() <= 0) {
            throw new IllegalArgumentException("Montant invalide");
        }
    }
    
}
