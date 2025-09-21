package com.apartment.management.repository;

import com.apartment.management.entity.TicketComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A TicketCommentsRepository.
 */
@Repository
public interface TicketCommentsRepository extends JpaRepository<TicketComments, Long> {

    /**
     * Get All Ticket Comments By ticketId
     *
     * @param ticketId ticketId
     * @return list of entity
     */
    List<TicketComments> findAllByApartmentTicketsIdOrderByCommentDateDesc(Long ticketId);
}
