package com.apartment.management.service.mapper;

import com.apartment.management.entity.ApartmentTickets;
import com.apartment.management.entity.enums.TicketStatus;
import com.apartment.management.service.dto.TicketRequestDTO;
import com.apartment.management.service.dto.TicketResponseDTO;
import com.apartment.management.service.dto.TicketStatusDTO;
import com.apartment.management.service.mapper.config.BaseMapperConfig;
import com.apartment.management.service.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * A ApartmentTicketsMapper.
 */
@Mapper(config = BaseMapperConfig.class, uses = {ApartmentsMapper.class})
public interface ApartmentTicketsMapper extends GenericMapper<ApartmentTickets, TicketRequestDTO> {

    @Override
    @Mapping(target = "apartments", source = "apartmentsId")
    ApartmentTickets toEntity(TicketRequestDTO dto);

    @Mapping(target = "ticketStatus", source = "ticketStatus")
    TicketResponseDTO toResponseDTO(ApartmentTickets apartmentTickets);

    default ApartmentTickets convertToEntity(Long id) {
        if (id == null) {
            return null;
        }
        return ApartmentTickets.builder().id(id).build();
    }

    default TicketStatusDTO mapTicketStatusToDTO(TicketStatus ticketStatus) {
        if (ticketStatus == null) {
            return null;
        }
        return TicketStatusDTO.builder()
                .name(ticketStatus.getName())
                .enumName(ticketStatus)
                .build();
    }
}
