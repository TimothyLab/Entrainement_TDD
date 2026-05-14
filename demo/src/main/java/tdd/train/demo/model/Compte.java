package tdd.train.demo.model;

import tdd.train.demo.exception.EchecVirementException;
import tdd.train.demo.exception.OverBalanceException;

public class Compte {

    private int balance;

    public Compte( int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    

    //méthode pour déposer de l'argent sur le compte
    public void deposer(int montant) {
        verifierMontantValide(montant);
        this.balance += montant;
    }


    //méthode pour retirer de l'argent du compte
    public void retirer(int montant) {
        verifierMontantValide(montant);
        verifierSolvabilite(montant);
        this.balance -= montant;
    
    }

    public void virement(Compte oc, int montant) {
        verifierMontantValide(montant);
        verifierSolvabilite(montant);
        this.retirer(montant);
        oc.deposer(montant);
        
    }

    public void verifierSolvabilite(int montant) {
        if (montant >this.balance) {
            throw new OverBalanceException("Le solde ne peut pas devenir négatif");
        }
    }

    private void verifierMontantValide(int montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Montant invalide");
        }
    }
    
}
