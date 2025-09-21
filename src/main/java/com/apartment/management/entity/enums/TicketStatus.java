package com.apartment.management.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * A TicketStatus
 */
@Getter
@RequiredArgsConstructor
public enum TicketStatus {

    PENDING("Pending", true), IN_PROGRESS("In Progress", true), IN_REVIEW("In Review", true), RESOLVED("Resolved", false), CANCEL("Cancel", false);

    private final String name;

    private final boolean visibility;
}
