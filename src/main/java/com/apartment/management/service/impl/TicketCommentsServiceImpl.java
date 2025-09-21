package com.apartment.management.service.impl;

import com.apartment.management.entity.TicketComments;
import com.apartment.management.entity.User;
import com.apartment.management.repository.TicketCommentsRepository;
import com.apartment.management.service.TicketCommentsService;
import com.apartment.management.service.dto.TicketCommentsDTO;
import com.apartment.management.service.mapper.TicketCommentsMapper;
import com.apartment.management.service.util.UserUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A TicketCommentsServiceImpl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TicketCommentsServiceImpl implements TicketCommentsService {

    private final TicketCommentsRepository ticketCommentsRepository;

    private final TicketCommentsMapper ticketCommentsMapper;

    @Override
    public void addCommentOnTicket(TicketCommentsDTO ticketCommentsDTO) {
        log.info("Request to add comment on ticket : {}", ticketCommentsDTO);
        User currentLoginUser = UserUtility.getCurrentLoginUser();
        TicketComments ticketComments = ticketCommentsMapper.toEntity(ticketCommentsDTO);
        ticketComments.setUser(currentLoginUser);
        ticketCommentsRepository.save(ticketComments);
    }

    @Override
    public List<TicketCommentsDTO> getAllCommentByTicketId(Long ticketId) {
        log.info("Request to get all ticket comment by ticketId : {}", ticketId);
        return ticketCommentsRepository.findAllByApartmentTicketsIdOrderByCommentDateDesc(ticketId)
                .stream().map(ticketCommentsMapper::toDto)
                .toList();
    }
}
