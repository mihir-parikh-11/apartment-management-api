package com.apartment.management.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A MiscellaneousTransactionDTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MiscellaneousTransactionDTO implements Serializable {

    @NotNull(message = "Apartment required")
    private Long apartmentsId;

    @NotNull(message = "Amount required")
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @Size(min = 5, max = 255, message = "Description must be between 5 and 255 characters")
    private String description;
}
