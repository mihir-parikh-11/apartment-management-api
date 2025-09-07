package com.apartment.management.service.mapper;

import com.apartment.management.entity.Gender;
import com.apartment.management.service.dto.GenderDTO;
import com.apartment.management.service.mapper.config.BaseMapperConfig;
import com.apartment.management.service.mapper.config.GenericMapper;
import org.mapstruct.Mapper;

/**
 * A GenderMapper.
 */
@Mapper(config = BaseMapperConfig.class)
public interface GenderMapper extends GenericMapper<Gender, GenderDTO> {

    default Gender convertToEntity(Long id) {
        if (id == null) {
            return null;
        }
        return Gender.builder().id(id).build();
    }
}
