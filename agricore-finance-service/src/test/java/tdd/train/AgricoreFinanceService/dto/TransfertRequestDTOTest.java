package tdd.train.AgricoreFinanceService.dto;

import tdd.train.AgricoreFinanceService.model.Liquidite;

public record TransfertRequestDTOTest (
    Integer sourceId,
    Integer destinationId,
    Liquidite montant
) {}
