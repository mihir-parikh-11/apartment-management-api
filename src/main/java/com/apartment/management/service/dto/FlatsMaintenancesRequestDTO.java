package com.apartment.management.service.dto;

import com.apartment.management.entity.enums.MaintenanceStatus;
import com.apartment.management.entity.enums.PaymentMode;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A FlatsMaintenancesDTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FlatsMaintenancesRequestDTO implements Serializable {

    @NotNull(message = "Id cannot be blank or null")
    private Long id;

    @NotNull(message = "Maintenance Status cannot be blank or null")
    private MaintenanceStatus maintenanceStatus;

    private PaymentMode paymentMode;
}
