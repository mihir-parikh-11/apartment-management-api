package com.apartment.management.service.dto;

import com.apartment.management.entity.enums.MaintenanceStatus;
import com.apartment.management.entity.enums.PaymentMode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Month;
import java.time.ZonedDateTime;

/**
 * A FlatsMaintenancesResponseDTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlatsMaintenancesResponseDTO implements Serializable {

    private Long id;

    private ApartmentBlockFlatsDTO flat;

    private Double maintenanceAmount;

    private MaintenanceStatus maintenanceStatus;

    private String challanNumber;

    private ZonedDateTime paidDate;

    private PaymentMode paymentMode;

    private ZonedDateTime dueDate;

    private Double dueAmount;

    private Month maintenanceMonth;

    private Integer maintenanceYear;

    private Double totalMaintenanceAmount;
}
