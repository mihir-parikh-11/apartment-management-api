package com.apartment.management.service.dto;

import com.apartment.management.entity.enums.ApartmentsStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A ApartmentsResponseDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApartmentsResponseDTO implements Serializable {

    private Long id;

    private String name;

    private Integer numberOfBlocks;

    private Double availableAmount;

    private Double dueAmount;

    private Double perFlatMaintenance;

    private Integer maintenanceDueDays;

    private Double chargesPerDayDue;

    private String address1;

    private String address2;

    private String city;

    private String postcode;

    private String state;

    private String country;

    private UserDTO manager;

    private Boolean status;

    private ApartmentsStatus apartmentsStatus;

    private UserDTO createdBy;

    private ZonedDateTime createdDate;

    private Integer totalFlats;
}
