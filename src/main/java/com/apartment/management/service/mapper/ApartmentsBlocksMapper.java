package com.apartment.management.service.mapper;

import com.apartment.management.entity.ApartmentsBlocks;
import com.apartment.management.service.dto.ApartmentsBlocksDTO;
import com.apartment.management.service.mapper.config.BaseMapperConfig;
import com.apartment.management.service.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class, uses = {ApartmentsMapper.class})
public interface ApartmentsBlocksMapper extends GenericMapper<ApartmentsBlocks, ApartmentsBlocksDTO> {

    @Override
    @Mapping(target = "apartments", source = "apartmentsId")
    ApartmentsBlocks toEntity(ApartmentsBlocksDTO dto);

    @Override
    ApartmentsBlocksDTO toDto(ApartmentsBlocks entity);

    @Override
    void updateEntityFromDto(ApartmentsBlocksDTO dto, @MappingTarget ApartmentsBlocks entity);

    default ApartmentsBlocks convertToEntity(Long id) {
        if (id == null) {
            return null;
        }
        return ApartmentsBlocks.builder().id(id).build();
    }
}
