package tdd.train.AgricoreFinanceService.exception;

public class CompteNotFoundException extends RuntimeException {

    public CompteNotFoundException(Integer id) {
        super("Compte avec l'id " + id + " introuvable");
    }

    public CompteNotFoundException(String string) {
        super(string);
    }

}
