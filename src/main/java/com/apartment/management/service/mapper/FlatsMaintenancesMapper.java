package com.apartment.management.service.mapper;

import com.apartment.management.entity.FlatsMaintenances;
import com.apartment.management.service.dto.FlatsMaintenancesResponseDTO;
import com.apartment.management.service.mapper.config.BaseMapperConfig;
import com.apartment.management.service.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Month;

/**
 * A FlatsMaintenancesMapper
 */
@Mapper(config = BaseMapperConfig.class, uses = {ApartmentBlockFlatsMapper.class})
public interface FlatsMaintenancesMapper extends GenericMapper<FlatsMaintenances, FlatsMaintenancesResponseDTO> {
    @Override
    @Mapping(target = "maintenanceMonth", source = "maintenanceMonth")
    FlatsMaintenances toEntity(FlatsMaintenancesResponseDTO dto);

    @Override
    @Mapping(target = "maintenanceMonth", source = "maintenanceMonth")
    FlatsMaintenancesResponseDTO toDto(FlatsMaintenances entity);

    default Month mapMonth(Integer month) {
        return month != null ? Month.of(month) : null;
    }

    default Integer mapMonth(Month month) {
        return month != null ? month.getValue() : null;
    }
}
