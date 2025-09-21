package com.apartment.management.entity;

import com.apartment.management.entity.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * A ApartmentTickets Entity
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "apartments_tickets")
public class ApartmentTickets implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "issue_name")
    private String issueName;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_status")
    private TicketStatus ticketStatus;

    @Column(name = "created_date", nullable = false, updatable = false)
    private ZonedDateTime createdDate;

    @Column(name = "resolved_date")
    private ZonedDateTime resolvedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartments_id")
    private Apartments apartments;

    @PrePersist
    protected void onCreate() {
        this.createdDate = ZonedDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate
    protected void onUpdate() {
        if (this.ticketStatus == TicketStatus.RESOLVED && this.resolvedDate == null) {
            this.resolvedDate = ZonedDateTime.now(ZoneOffset.UTC);
        }
    }
}
