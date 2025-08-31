package com.apartment.management.service.mapper.config;

import com.apartment.management.entity.Gender;
import com.apartment.management.service.dto.GenderDTO;
import org.mapstruct.Mapper;

/**
 * A UserMapper.
 */
@Mapper(config = BaseMapperConfig.class)
public interface GenderMapper extends GenericMapper<Gender, GenderDTO> {
}
