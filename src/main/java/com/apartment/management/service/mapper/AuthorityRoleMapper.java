package com.apartment.management.service.mapper;

import com.apartment.management.entity.AuthorityRole;
import com.apartment.management.service.dto.AuthorityRoleDTO;
import com.apartment.management.service.mapper.config.BaseMapperConfig;
import com.apartment.management.service.mapper.config.GenericMapper;
import org.mapstruct.Mapper;

/**
 * A AuthorityRoleMapper
 */
@Mapper(config = BaseMapperConfig.class)
public interface AuthorityRoleMapper extends GenericMapper<AuthorityRole, AuthorityRoleDTO> {
}
