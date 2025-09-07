package com.apartment.management.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A ApartmentBlockFlatsDTO
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApartmentBlockFlatsDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Flat number cannot be blank")
    private String flatNumber;

    @NotNull(message = "Floor Number required")
    private Integer floorNumber;

    private Long blocksId;

    private double paidMaintenance;

    private double dueMaintenance;

    private FlatOwnerDTO owner;
}
