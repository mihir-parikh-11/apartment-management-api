package com.apartment.management.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * A TicketRequestDTO.
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketRequestDTO {

    private Long id;

    @NotBlank(message = "Issue name is required")
    private String issueName;

    @NotBlank(message = "Description is required")
    private String description;

    private Long apartmentsId;
}
