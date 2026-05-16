package tdd.train.AgricoreFinanceService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "compte")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compte_id")
    private Integer id;

    @Embedded
    private Liquidite balance;


    private Integer userId;


    
    public Compte() {
    }

    public Compte( Liquidite balance) {
        this.balance = balance;
    }
    public Compte( Liquidite balance,Integer userId) {
        this.userId = userId;
        this.balance = balance;
    }

    public Compte(Integer id, Liquidite balance) {
        this.id = id;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public Liquidite getBalance() {
        return balance;
    }

    


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    //méthode pour déposer de l'argent sur le compte
    public void deposer(Liquidite montant) {
        
        this.balance = balance.ajouter(montant);
    }


    //méthode pour retirer de l'argent du compte
    public void retirer(Liquidite montant) {
       
        this.balance = balance.retirer(montant);
    
    }

    public void changerBalance(Liquidite newbalance) {
        this.balance= newbalance;
    }


  
    
}
