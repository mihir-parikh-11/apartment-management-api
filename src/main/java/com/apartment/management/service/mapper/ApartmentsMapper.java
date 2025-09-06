package com.apartment.management.service.mapper;

import com.apartment.management.entity.Apartments;
import com.apartment.management.service.dto.ApartmentsRequestDTO;
import com.apartment.management.service.dto.ApartmentsResponseDTO;
import com.apartment.management.service.mapper.config.BaseMapperConfig;
import com.apartment.management.service.mapper.config.GenericMapper;
import org.mapstruct.*;


/**
 * A ApartmentsMapper
 */
@Mapper(config = BaseMapperConfig.class, uses = {UserMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ApartmentsMapper extends GenericMapper<Apartments, ApartmentsRequestDTO> {

    @Override
    Apartments toEntity(ApartmentsRequestDTO dto);

    @Override
    ApartmentsRequestDTO toDto(Apartments entity);

    ApartmentsResponseDTO toResponseDto(Apartments entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "availableAmount", ignore = true)
    @Mapping(target = "dueAmount", ignore = true)
    void updateEntityFromDto(ApartmentsRequestDTO dto, @MappingTarget Apartments entity);

    default Apartments convertToEntity(Long id) {
        if (id == null) {
            return null;
        }
        return Apartments.builder().id(id).build();
    }
}
