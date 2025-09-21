package com.apartment.management.service;

import com.apartment.management.entity.ApartmentTickets;
import com.apartment.management.entity.enums.TicketStatus;
import com.apartment.management.service.dto.TicketDetailsDTO;
import com.apartment.management.service.dto.TicketRequestDTO;
import com.apartment.management.service.dto.TicketResponseDTO;
import com.apartment.management.service.dto.TicketStatusDTO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * A ApartmentTicketsService.
 */
public interface ApartmentTicketsService {

    /**
     * Find By id
     *
     * @param id apartmentTicketsId
     * @return Entity
     */
    ApartmentTickets findById(Long id);

    /**
     * Create Ticket
     *
     * @param ticketRequestDTO DTO
     */
    void createTicket(TicketRequestDTO ticketRequestDTO);

    /**
     * Update Ticket
     *
     * @param ticketRequestDTO DTO
     * @return DTO
     */
    TicketResponseDTO updateTicket(@Valid TicketRequestDTO ticketRequestDTO);

    /**
     * Update Ticket status
     *
     * @param ticketId     ticketId
     * @param ticketStatus status
     * @return DTO
     */
    TicketResponseDTO updateTicketStatusByApartmentManager(Long ticketId, TicketStatus ticketStatus);

    /**
     * Ticket Resolved By Created User
     *
     * @param ticketId ticketId
     * @return DTO
     */
    TicketResponseDTO ticketResolvedByUser(Long ticketId);

    /**
     * Ticket Cancel By Created User
     *
     * @param ticketId ticketId
     * @return DTO
     */
    TicketResponseDTO ticketCancelByUser(Long ticketId);

    /**
     * Get ALl Tickets By Apartment id
     *
     * @param apartmentId apartmentId
     * @return list of DTO
     */
    List<TicketResponseDTO> getAllByApartment(Long apartmentId);

    /**
     * Get Ticket Details By id
     *
     * @param ticketId ticketId
     * @return DTO
     */
    TicketDetailsDTO getTicketDetailsById(Long ticketId);

    /**
     * Get All Ticket Status
     *
     * @return list of DTO
     */
    List<TicketStatusDTO> getAllTicketStatus();
}
