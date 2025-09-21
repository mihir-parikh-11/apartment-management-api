package com.apartment.management.service.impl;

import com.apartment.management.entity.ApartmentTickets;
import com.apartment.management.entity.User;
import com.apartment.management.entity.enums.TicketStatus;
import com.apartment.management.exception.GlobalException;
import com.apartment.management.repository.ApartmentTicketsRepository;
import com.apartment.management.service.ApartmentTicketsService;
import com.apartment.management.service.TicketCommentsService;
import com.apartment.management.service.dto.TicketDetailsDTO;
import com.apartment.management.service.dto.TicketRequestDTO;
import com.apartment.management.service.dto.TicketResponseDTO;
import com.apartment.management.service.dto.TicketStatusDTO;
import com.apartment.management.service.mapper.ApartmentTicketsMapper;
import com.apartment.management.service.util.UserUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * A ApartmentTicketsServiceImpl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ApartmentTicketsServiceImpl implements ApartmentTicketsService {

    private final ApartmentTicketsRepository apartmentTicketsRepository;

    private final ApartmentTicketsMapper apartmentTicketsMapper;

    private final TicketCommentsService ticketCommentsService;

    @Override
    public ApartmentTickets findById(Long id) {
        return apartmentTicketsRepository.findById(id).orElseThrow(() -> new GlobalException("Ticket not found"));
    }

    @Override
    public void createTicket(TicketRequestDTO ticketRequestDTO) {
        log.info("Request to create Ticket : {}", ticketRequestDTO);
        if (ticketRequestDTO.getApartmentsId() == null) {
            throw new GlobalException("Apartments Id is required");
        }
        ApartmentTickets apartmentTickets = apartmentTicketsMapper.toEntity(ticketRequestDTO);
        apartmentTickets.setTicketStatus(TicketStatus.PENDING);
        apartmentTickets.setCreatedBy(UserUtility.getCurrentLoginUser());
        apartmentTicketsRepository.save(apartmentTickets);
    }

    @Override
    public TicketResponseDTO updateTicket(TicketRequestDTO ticketRequestDTO) {
        log.info("Request to Update Ticket : {}", ticketRequestDTO);
        User currentLoginUser = UserUtility.getCurrentLoginUser();
        ApartmentTickets apartmentTickets = findById(ticketRequestDTO.getId());
        if (!currentLoginUser.getId().equals(apartmentTickets.getCreatedBy().getId())) {
            throw new AccessDeniedException("User is not authorized to perform this action.");
        }
        if (!(apartmentTickets.getTicketStatus() == TicketStatus.PENDING)) {
            throw new GlobalException("Ticket status must be Pending for Update Ticket");
        }
        apartmentTickets.setIssueName(ticketRequestDTO.getIssueName());
        apartmentTickets.setDescription(ticketRequestDTO.getDescription());
        return apartmentTicketsMapper.toResponseDTO(apartmentTicketsRepository.save(apartmentTickets));
    }

    @Override
    public TicketResponseDTO updateTicketStatusByApartmentManager(Long ticketId, TicketStatus ticketStatus) {
        log.info("Request to update ticket status : {} by id : {}", ticketStatus, ticketId);
        User currentLoginUser = UserUtility.getCurrentLoginUser();
        ApartmentTickets apartmentTickets = findById(ticketId);
        if (!currentLoginUser.getId().equals(apartmentTickets.getApartments().getManager().getId())) {
            throw new AccessDeniedException("User is not authorized to perform this action.");
        }
        TicketStatus existingTicketStatus = apartmentTickets.getTicketStatus();
        if (existingTicketStatus == TicketStatus.PENDING || existingTicketStatus == TicketStatus.IN_PROGRESS) {
            apartmentTickets.setTicketStatus(ticketStatus);
            apartmentTickets = apartmentTicketsRepository.save(apartmentTickets);
            TicketResponseDTO ticketResponseDTO = apartmentTicketsMapper.toResponseDTO(apartmentTickets);
            ticketResponseDTO.setUserAbleToUpdateStatus(ticketStatus == TicketStatus.PENDING || ticketStatus == TicketStatus.IN_PROGRESS);
            return ticketResponseDTO;
        } else {
            throw new GlobalException("Something went wrong");
        }
    }

    @Override
    public TicketResponseDTO ticketResolvedByUser(Long ticketId) {
        log.info("Request to ticket resolved by user by ticketId : {}", ticketId);
        User currentLoginUser = UserUtility.getCurrentLoginUser();
        ApartmentTickets apartmentTickets = findById(ticketId);
        if (!currentLoginUser.getId().equals(apartmentTickets.getCreatedBy().getId())) {
            throw new AccessDeniedException("User is not authorized to perform this action.");
        }
        apartmentTickets.setTicketStatus(TicketStatus.RESOLVED);
        apartmentTickets = apartmentTicketsRepository.save(apartmentTickets);
        return apartmentTicketsMapper.toResponseDTO(apartmentTickets);
    }

    @Override
    public TicketResponseDTO ticketCancelByUser(Long ticketId) {
        log.info("Request to ticket Cancel by user by ticketId : {}", ticketId);
        User currentLoginUser = UserUtility.getCurrentLoginUser();
        ApartmentTickets apartmentTickets = findById(ticketId);
        if (!currentLoginUser.getId().equals(apartmentTickets.getCreatedBy().getId())) {
            throw new AccessDeniedException("User is not authorized to perform this action.");
        }
        if (!(apartmentTickets.getTicketStatus() == TicketStatus.PENDING)) {
            throw new GlobalException("Ticket status must be Pending for Cancel Ticket");
        }
        apartmentTickets.setTicketStatus(TicketStatus.CANCEL);
        apartmentTickets = apartmentTicketsRepository.save(apartmentTickets);
        return apartmentTicketsMapper.toResponseDTO(apartmentTickets);
    }

    @Override
    public List<TicketResponseDTO> getAllByApartment(Long apartmentId) {
        log.info("Request to get All Tickets by Apartment Id : {}", apartmentId);
        List<ApartmentTickets> apartmentTicketsList = apartmentTicketsRepository.findAllByApartmentsIdOrderByCreatedDateDescIdDesc(apartmentId);
        return apartmentTicketsList.stream()
                .map(apartmentTicketsMapper::toResponseDTO)
                .toList();
    }

    @Override
    public TicketDetailsDTO getTicketDetailsById(Long ticketId) {
        log.info("REST request to get ticket details by id : {}", ticketId);
        String currentLoginUserId = String.valueOf(UserUtility.getCurrentLoginUser().getId());
        ApartmentTickets apartmentTickets = findById(ticketId);
        TicketResponseDTO ticketResponseDTO = apartmentTicketsMapper.toResponseDTO(apartmentTickets);
        TicketStatus ticketStatus = apartmentTickets.getTicketStatus();
        if (!(ticketStatus == TicketStatus.CANCEL || ticketStatus == TicketStatus.RESOLVED)) {
            String managerId = String.valueOf(apartmentTickets.getApartments().getManager().getId());
            String createdById = String.valueOf(apartmentTickets.getCreatedBy().getId());
            if (StringUtils.equalsAny(currentLoginUserId, managerId, createdById)) {
                handleTicketPermissions(ticketResponseDTO, currentLoginUserId, managerId, createdById, ticketStatus);
            }
        }
        return TicketDetailsDTO.builder()
                .ticketDetails(ticketResponseDTO)
                .comments(ticketCommentsService.getAllCommentByTicketId(ticketId))
                .build();
    }

    @Override
    public List<TicketStatusDTO> getAllTicketStatus() {
        log.info("Request to get All Ticket Status");
        return Arrays.stream(TicketStatus.values()).filter(TicketStatus::isVisibility)
                .map(ticketStatus -> TicketStatusDTO.builder().name(ticketStatus.getName()).enumName(ticketStatus).build())
                .toList();
    }

    /**
     * Handles ticket permissions for the current user based on their role and ticket status.
     * Depending on whether the user is a manager, creator, or both, this method
     * sets the appropriate permissions in the ticket response DTO.
     *
     * @param ticketResponseDTO  The DTO containing ticket details.
     * @param currentLoginUserId The ID of the current logged-in user.
     * @param managerId          The ID of the manager associated with the ticket.
     * @param createdById        The ID of the user who created the ticket.
     * @param ticketStatus       The current status of the ticket.
     * @throws GlobalException if the ticket status logic for a given role is not implemented.
     */
    private void handleTicketPermissions(TicketResponseDTO ticketResponseDTO, String currentLoginUserId, String managerId, String createdById, TicketStatus ticketStatus) {
        if (StringUtils.equals(currentLoginUserId, managerId) && StringUtils.equals(currentLoginUserId, createdById)) {
            handleManagerAndCreatorPermissions(ticketResponseDTO, ticketStatus);
        } else if (StringUtils.equals(currentLoginUserId, managerId)) {
            handleManagerPermissions(ticketResponseDTO, ticketStatus);
        } else {
            handleCreatorPermissions(ticketResponseDTO, ticketStatus);
        }
    }

    /**
     * Handles permissions for the case where the current user is both the manager and the creator.
     * Sets the appropriate permissions based on the ticket's status.
     *
     * @param ticketResponseDTO The DTO containing ticket details.
     * @param ticketStatus      The current status of the ticket.
     * @throws GlobalException if the ticket status logic is not implemented.
     */
    private void handleManagerAndCreatorPermissions(TicketResponseDTO ticketResponseDTO, TicketStatus ticketStatus) {
        switch (ticketStatus) {
            case PENDING:
                ticketResponseDTO.setUserAbleToCancelTicket(true);
                ticketResponseDTO.setUserAbleToUpdateStatus(true);
                break;
            case IN_PROGRESS:
            case IN_REVIEW:
                ticketResponseDTO.setUserAbleToUpdateStatus(true);
                break;
            default:
                throw new GlobalException("Ticket status logic not implemented for manager and creator");
        }
    }

    /**
     * Handles permissions for the case where the current user is the manager.
     * Sets the appropriate permissions based on the ticket's status.
     *
     * @param ticketResponseDTO The DTO containing ticket details.
     * @param ticketStatus      The current status of the ticket.
     * @throws GlobalException if the ticket status logic is not implemented.
     */
    private void handleManagerPermissions(TicketResponseDTO ticketResponseDTO, TicketStatus ticketStatus) {
        switch (ticketStatus) {
            case PENDING:
            case IN_PROGRESS:
                ticketResponseDTO.setUserAbleToUpdateStatus(true);
                break;
            case IN_REVIEW:
                break;
            default:
                throw new GlobalException("Ticket status logic not implemented for manager");
        }
    }

    /**
     * Handles permissions for the case where the current user is the creator of the ticket.
     * Sets the appropriate permissions based on the ticket's status.
     *
     * @param ticketResponseDTO The DTO containing ticket details.
     * @param ticketStatus      The current status of the ticket.
     * @throws GlobalException if the ticket status logic is not implemented.
     */
    private void handleCreatorPermissions(TicketResponseDTO ticketResponseDTO, TicketStatus ticketStatus) {
        switch (ticketStatus) {
            case PENDING:
                ticketResponseDTO.setUserAbleToCancelTicket(true);
                ticketResponseDTO.setUserAbleToUpdateStatus(true);
                break;
            case IN_REVIEW:
                ticketResponseDTO.setUserAbleToUpdateStatus(true);
                break;
            case IN_PROGRESS:
                break;
            default:
                throw new GlobalException("Ticket status logic not implemented for creator");
        }
    }
}
