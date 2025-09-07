package com.apartment.management.service.mapper;

import com.apartment.management.entity.ApartmentsBlocks;
import com.apartment.management.service.dto.ApartmentsBlocksDTO;
import com.apartment.management.service.mapper.config.BaseMapperConfig;
import com.apartment.management.service.mapper.config.GenericMapper;
import org.mapstruct.*;

/**
 * A ApartmentsBlocksMapper
 */
@Mapper(config = BaseMapperConfig.class, uses = {ApartmentsMapper.class, ApartmentBlockFlatsMapper.class})
public interface ApartmentsBlocksMapper extends GenericMapper<ApartmentsBlocks, ApartmentsBlocksDTO> {

    @Override
    @Mapping(target = "apartments", source = "apartmentsId")
    @Mapping(target = "flats", ignore = true)
    ApartmentsBlocks toEntity(ApartmentsBlocksDTO dto);

    @Override
    @Mapping(target = "flats", source = "flats", qualifiedByName = "convertListToMap")
    ApartmentsBlocksDTO toDto(ApartmentsBlocks entity);

    @Override
    @Mapping(target = "flats", ignore = true)
    void updateEntityFromDto(ApartmentsBlocksDTO dto, @MappingTarget ApartmentsBlocks entity);

    default ApartmentsBlocks convertToEntity(Long id) {
        if (id == null) {
            return null;
        }
        return ApartmentsBlocks.builder().id(id).build();
    }
}
