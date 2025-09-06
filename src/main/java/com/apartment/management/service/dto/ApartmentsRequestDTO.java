package com.apartment.management.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A ApartmentsRequestDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApartmentsRequestDTO implements Serializable {

    private Long id;

    private String name;

    private double availableAmount;

    private double perFlatMaintenance;

    private int maintenanceDueDays;

    private double chargesPerDayDue;

    private String address1;

    private String address2;

    private String city;

    private String postcode;

    private String state;

    private String country;
}
