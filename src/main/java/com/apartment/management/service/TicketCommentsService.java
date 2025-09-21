package com.apartment.management.service;

import com.apartment.management.service.dto.TicketCommentsDTO;

import java.util.List;

/**
 * A TicketCommentsService.
 */
public interface TicketCommentsService {

    /**
     * Add Comment on Ticket
     *
     * @param ticketCommentsDTO DTO
     */
    void addCommentOnTicket(TicketCommentsDTO ticketCommentsDTO);

    /**
     * Get All Comment By ticketId
     *
     * @param ticketId ticketId
     * @return list of DTO
     */
    List<TicketCommentsDTO> getAllCommentByTicketId(Long ticketId);
}
