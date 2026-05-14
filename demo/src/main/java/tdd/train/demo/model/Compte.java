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

    public void setBalance(int balance) {
        this.balance = balance;
    }

    //méthode pour déposer de l'argent sur le compte
    public void deposer(int montant) {
        this.balance += montant;
    }


    //méthode pour retirer de l'argent du compte
    public void retirer(int montant) {

        if (montant <= this.balance) {

        this.balance -= montant;
        } else { throw new OverBalanceException("Le solde ne peut pas devenir négatif");
        }
    }

    public void virement(Compte oc, int montant) {
        if (montant < this.balance) {
        this.retirer(montant);
        oc.deposer(montant);
        } else { throw new EchecVirementException("Le virement a échoué, solde insuffisant");
        }
    }
    
}
