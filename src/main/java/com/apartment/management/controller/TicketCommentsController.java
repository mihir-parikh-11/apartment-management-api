package com.apartment.management.controller;

import com.apartment.management.service.TicketCommentsService;
import com.apartment.management.service.dto.TicketCommentsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A TicketCommentsController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket-comment")
@Slf4j
public class TicketCommentsController {

    private final TicketCommentsService ticketCommentsService;

    /**
     * Add Comment on Ticket
     *
     * @param ticketCommentsDTO DTO
     */
    @PostMapping
    public ResponseEntity<Void> addCommentOnTicket(@RequestBody TicketCommentsDTO ticketCommentsDTO) {
        log.info("REST request to add comment on ticket : {}", ticketCommentsDTO);
        ticketCommentsService.addCommentOnTicket(ticketCommentsDTO);
        return ResponseEntity.ok().build();
    }
}
