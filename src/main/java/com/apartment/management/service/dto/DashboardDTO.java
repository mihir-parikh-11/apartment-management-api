package com.apartment.management.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DashboardDTO.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DashboardDTO implements Serializable {

    private long totalUsers;

    private long totalManagers;

    private long totalOwners;

    private long totalAdmins;

    private long totalApartments;

    private long totalPendingApartments;

    private long totalApprovedApartments;

    private long totalRejectedApartments;

    private long totalBlocks;

    private long totalFlats;

    private long totalTickets;

    private long totalPendingTickets;

    private long totalInProgressTickets;

    private long totalInReviewTickets;

    private long totalResolvedTickets;

    private long totalCancelTickets;
}
