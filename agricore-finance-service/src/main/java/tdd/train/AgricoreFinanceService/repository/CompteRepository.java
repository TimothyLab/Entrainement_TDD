package tdd.train.AgricoreFinanceService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tdd.train.AgricoreFinanceService.model.Compte;

public interface CompteRepository extends JpaRepository<Compte,Integer> {

    Compte findByUserId(Integer userId);
}
