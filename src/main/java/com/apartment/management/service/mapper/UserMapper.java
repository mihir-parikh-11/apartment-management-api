package com.apartment.management.service.mapper;

import com.apartment.management.entity.User;
import com.apartment.management.service.dto.RegisterUserDTO;
import com.apartment.management.service.dto.UserDTO;
import com.apartment.management.service.mapper.config.BaseMapperConfig;
import com.apartment.management.service.mapper.config.GenderMapper;
import com.apartment.management.service.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * A UserMapper.
 */
@Mapper(config = BaseMapperConfig.class, uses = {AuthorityRoleMapper.class, GenderMapper.class})
public interface UserMapper extends GenericMapper<User, UserDTO> {

    @Override
    @Mapping(target = "username", source = "email")
    User toEntity(UserDTO dto);

    @Override
    UserDTO toDto(User entity);

    @Mapping(target = "username", source = "email")
    @Mapping(target = "password", ignore = true)
    User toEntity(RegisterUserDTO dto);
}
