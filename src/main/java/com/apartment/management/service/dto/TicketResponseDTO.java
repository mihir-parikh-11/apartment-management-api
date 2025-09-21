package com.apartment.management.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A TicketResponseDTO
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketResponseDTO implements Serializable {

    private Long id;

    private String issueName;

    private String description;

    private UserDTO createdBy;

    private TicketStatusDTO ticketStatus;

    private ZonedDateTime createdDate;

    private ZonedDateTime resolvedDate;

    private boolean isUserAbleToUpdateStatus;

    private boolean isUserAbleToCancelTicket;
}
