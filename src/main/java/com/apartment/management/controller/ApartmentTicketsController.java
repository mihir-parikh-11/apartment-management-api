package com.apartment.management.controller;

import com.apartment.management.entity.enums.TicketStatus;
import com.apartment.management.service.ApartmentTicketsService;
import com.apartment.management.service.dto.TicketDetailsDTO;
import com.apartment.management.service.dto.TicketRequestDTO;
import com.apartment.management.service.dto.TicketResponseDTO;
import com.apartment.management.service.dto.TicketStatusDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A ApartmentTicketsController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
@Slf4j
public class ApartmentTicketsController {

    private final ApartmentTicketsService apartmentTicketsService;

    /**
     * Create Ticket
     *
     * @param ticketRequestDTO DTO
     * @return Void
     */
    @PostMapping
    public ResponseEntity<Void> createTicket(@Valid @RequestBody TicketRequestDTO ticketRequestDTO) {
        log.info("REST request to Create Ticket : {}", ticketRequestDTO);
        apartmentTicketsService.createTicket(ticketRequestDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Update Ticket
     *
     * @param ticketRequestDTO DTO
     * @return DTO
     */
    @PutMapping
    public ResponseEntity<TicketResponseDTO> updateTicket(@Valid @RequestBody TicketRequestDTO ticketRequestDTO) {
        log.info("REST request to Update Ticket : {}", ticketRequestDTO);
        return ResponseEntity.ok(apartmentTicketsService.updateTicket(ticketRequestDTO));
    }

    /**
     * Update Ticket status
     *
     * @param ticketId     ticketId
     * @param ticketStatus status
     * @return DTO
     */
    @PutMapping("/{ticketId}/status")
    public ResponseEntity<TicketResponseDTO> updateTicketStatusByApartmentManager(@PathVariable(name = "ticketId") Long ticketId,
                                                                                  @RequestParam(name = "ticketStatus") TicketStatus ticketStatus) {
        log.info("REST request to update ticket status : {} by id : {}", ticketStatus, ticketId);
        return ResponseEntity.ok(apartmentTicketsService.updateTicketStatusByApartmentManager(ticketId, ticketStatus));
    }

    /**
     * Ticket Resolved By Created User
     *
     * @param ticketId ticketId
     * @return DTO
     */
    @PutMapping("/{ticketId}/resolved")
    public ResponseEntity<TicketResponseDTO> ticketResolvedByUser(@PathVariable(name = "ticketId") Long ticketId) {
        log.info("REST request to ticket resolved by user by ticketId : {}", ticketId);
        return ResponseEntity.ok(apartmentTicketsService.ticketResolvedByUser(ticketId));
    }

    /**
     * Ticket Cancel By Created User
     *
     * @param ticketId ticketId
     * @return DTO
     */
    @PutMapping("/{ticketId}/cancel")
    public ResponseEntity<TicketResponseDTO> ticketCancelByUser(@PathVariable(name = "ticketId") Long ticketId) {
        log.info("REST request to ticket Cancel by user by ticketId : {}", ticketId);
        return ResponseEntity.ok(apartmentTicketsService.ticketCancelByUser(ticketId));
    }

    /**
     * Get ALl Tickets By Apartment id
     *
     * @param apartmentId apartmentId
     * @return list of DTO
     */
    @GetMapping("/all")
    public ResponseEntity<List<TicketResponseDTO>> getAllByApartment(@RequestParam(name = "apartmentId") Long apartmentId) {
        log.info("REST request to get All Tickets by Apartment Id : {}", apartmentId);
        return ResponseEntity.ok(apartmentTicketsService.getAllByApartment(apartmentId));
    }

    /**
     * Get Ticket Details By id
     *
     * @param ticketId ticketId
     * @return DTO
     */
    @GetMapping
    public ResponseEntity<TicketDetailsDTO> getTicketDetailsById(@RequestParam(name = "ticketId") Long ticketId) {
        log.info("REST request to get ticket details by id : {}", ticketId);
        return ResponseEntity.ok(apartmentTicketsService.getTicketDetailsById(ticketId));
    }

    /**
     * Get All Ticket Status
     *
     * @return list of DTO
     */
    @GetMapping("/status")
    public ResponseEntity<List<TicketStatusDTO>> getAllTicketStatus() {
        log.info("REST request to get All Ticket Status");
        return ResponseEntity.ok(apartmentTicketsService.getAllTicketStatus());
    }
}
