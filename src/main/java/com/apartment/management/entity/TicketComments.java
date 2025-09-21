package com.apartment.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * A TicketComments Entity.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "ticket_comments")
public class TicketComments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartments_tickets_id")
    private ApartmentTickets apartmentTickets;

    @Column(name = "comment_date")
    private ZonedDateTime commentDate;

    @PrePersist
    protected void onCreate() {
        this.commentDate = ZonedDateTime.now(ZoneOffset.UTC);
    }
}
