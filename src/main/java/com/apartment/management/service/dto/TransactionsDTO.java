package com.apartment.management.service.dto;

import com.apartment.management.entity.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

/**
 * A TransactionsDTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionsDTO implements Serializable {

    private Long id;

    private Double amount;

    private String description;

    private TransactionType transactionType;

    private Date transactionDate;

    private boolean isMaintenanceAmount;

    private FlatsMaintenancesResponseDTO flatsMaintenances;

    private Double remainingBalance;
}
