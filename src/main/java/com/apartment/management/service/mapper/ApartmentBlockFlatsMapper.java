package com.apartment.management.service.mapper;

import com.apartment.management.entity.ApartmentBlockFlats;
import com.apartment.management.service.dto.ApartmentBlockFlatsDTO;
import com.apartment.management.service.mapper.config.BaseMapperConfig;
import com.apartment.management.service.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * A ApartmentBlockFlatsMapper
 */
@Mapper(config = BaseMapperConfig.class, uses = {GenderMapper.class, UserMapper.class, ApartmentsBlocksMapper.class})
public interface ApartmentBlockFlatsMapper extends GenericMapper<ApartmentBlockFlats, ApartmentBlockFlatsDTO> {

    @Override
    @Mapping(target = "apartmentsBlocks", source = "blocksId")
    ApartmentBlockFlats toEntity(ApartmentBlockFlatsDTO dto);

    @Override
    ApartmentBlockFlatsDTO toDto(ApartmentBlockFlats entity);

    default ApartmentBlockFlats convertToEntity(Long id) {
        if (id == null) {
            return null;
        }
        return ApartmentBlockFlats.builder().id(id).build();
    }

    @Named("convertListToMap")
    default Map<Integer, List<ApartmentBlockFlatsDTO>> convertListToMap(List<ApartmentBlockFlats> apartmentBlockFlats) {
        if (apartmentBlockFlats == null || apartmentBlockFlats.isEmpty()) {
            return new TreeMap<>();
        }
        return apartmentBlockFlats.stream()
                .map(this::toDto)
                .collect(Collectors.groupingBy(ApartmentBlockFlatsDTO::getFloorNumber, TreeMap::new, Collectors.toList()));
    }
}
