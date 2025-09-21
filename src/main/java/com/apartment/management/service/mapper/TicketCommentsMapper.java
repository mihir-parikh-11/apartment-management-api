package com.apartment.management.service.mapper;

import com.apartment.management.entity.TicketComments;
import com.apartment.management.service.dto.TicketCommentsDTO;
import com.apartment.management.service.mapper.config.BaseMapperConfig;
import com.apartment.management.service.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * A TicketCommentsMapper
 */
@Mapper(config = BaseMapperConfig.class, uses = {ApartmentTicketsMapper.class})
public interface TicketCommentsMapper extends GenericMapper<TicketComments, TicketCommentsDTO> {

    @Override
    @Mapping(target = "apartmentTickets", source = "ticketsId")
    TicketComments toEntity(TicketCommentsDTO dto);
}
